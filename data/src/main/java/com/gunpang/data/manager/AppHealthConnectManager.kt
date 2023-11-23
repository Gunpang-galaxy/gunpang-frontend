package com.gunpang.data.manager

import android.content.Context
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.mutableStateOf
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.HealthConnectClient.Companion.SDK_AVAILABLE
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.changes.Change
import androidx.health.connect.client.readRecord
import androidx.health.connect.client.records.BodyFatRecord
import androidx.health.connect.client.records.BodyWaterMassRecord
import androidx.health.connect.client.records.BoneMassRecord
import androidx.health.connect.client.records.ExerciseSessionRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.SleepSessionRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.records.WeightRecord
import androidx.health.connect.client.request.ChangesTokenRequest
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.time.Instant

class AppHealthConnectManager (private val context: Context) {
    private val healthConnectClient by lazy { HealthConnectClient.getOrCreate(context) }

    var availability = mutableStateOf(HealthConnectAvailability.NOT_SUPPORTED)
        private set
    init {
        checkAvailability()
    }

    fun checkAvailability() {
        availability.value = when {
            HealthConnectClient.getSdkStatus(context) == SDK_AVAILABLE -> HealthConnectAvailability.INSTALLED
            else -> HealthConnectAvailability.NOT_INSTALLED
        }
    }

    /**
     * Determines whether all the specified permissions are already granted. It is recommended to
     * call [PermissionController.getGrantedPermissions] first in the permissions flow, as if the
     * permissions are already granted then there is no need to request permissions via
     * [PermissionController.createRequestPermissionResultContract].
     */
    suspend fun hasAllPermissions(permissions: Set<String>): Boolean {
        return healthConnectClient.permissionController.getGrantedPermissions().containsAll(permissions)
    }

    fun requestPermissionsActivityContract(): ActivityResultContract<Set<String>, Set<String>> {
        Log.d("[permission request]", "requestPermissionsActivityContract")
        return PermissionController.createRequestPermissionResultContract()
    }

    /**
     * 지난 start - end일 동안의 수면 기록을 들고옮
     */
    suspend fun readSleepInputs(start: Instant, end: Instant) : List<Instant> {
        val request = ReadRecordsRequest(
            recordType = SleepSessionRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        if(response.records.isNotEmpty()) {
            Log.d("[readSleepInputsStartTime]", response.records.last().startTime.toString())
            Log.d("[readSleepInputsEndTime]", response.records.last().endTime.toString())
            return listOf(response.records.last().startTime, response.records.last().endTime)
        }
        return listOf(Instant.now())
    }


    suspend fun readWeightInputs(start: Instant, end: Instant): Double{
        // TODO: appHealthConnectClient를 통해서 체중 받아오기
        val request = ReadRecordsRequest(
            recordType = WeightRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        if(response.records.isNotEmpty()){
        Log.d("[readWeightInputs]", response.records.last().weight.inKilograms.toString())
        return response.records.last().weight.inKilograms}
        return 0.0
    }

    suspend fun readBodyFatInput(start: Instant, end: Instant): Double{
        // TODO: appHealthConnectClient를 통해서 체지방량 받아오기
        val request = ReadRecordsRequest(
            recordType = BodyFatRecord::class,
            timeRangeFilter = TimeRangeFilter.between(start, end)
        )
        val response = healthConnectClient.readRecords(request)
        if(response.records.isNotEmpty()){
        Log.d("[readBodyFatInput]", response.records.last().percentage.value.toString())
        return response.records.last().percentage.value}
        return 0.0
    }

//    suspend fun readBodyWaterMassInput(start: Instant, end: Instant): Double{
//        // TODO: appHealthConnectClient를 통해서 체수분량 받아오기
//        val request = ReadRecordsRequest(
//            recordType = BodyWaterMassRecord::class,
//            timeRangeFilter = TimeRangeFilter.between(start, end)
//        )
//        val response = healthConnectClient.readRecords(request)
//        if(response.records.isNotEmpty()){
//        Log.d("[readBodyWaterMassInput]", response.records.last().mass.inKilograms.toString())
//        return response.records.last().mass.inKilograms}
//        return 0.0
//    }

//    suspend fun readBoneMassInput(start: Instant, end: Instant): Double{
//        // TODO: appHealthConnectClient를 통해서 골격근량 받아오기
//        val request = ReadRecordsRequest(
//            recordType = BoneMassRecord::class,
//            timeRangeFilter = TimeRangeFilter.between(start, end)
//        )
//        val response = healthConnectClient.readRecords(request)
//        if(response.records.isNotEmpty()){
//            Log.d("[readBoneMassInput]", response.records.last().mass.inKilograms.toString())
//            return response.records.last().mass.inKilograms}
//        return 0.0
//    }
    /**
     * Retrieve changes from a changes token.
     */
    suspend fun getChanges(token: String): Flow<ChangesMessage> = flow {
        var nextChangesToken = token
        do {
            val response = healthConnectClient.getChanges(nextChangesToken)
            if (response.changesTokenExpired) {
                // As described here: https://developer.android.com/guide/health-and-fitness/health-connect/data-and-data-types/differential-changes-api
                // tokens are only valid for 30 days. It is important to check whether the token has
                // expired. As well as ensuring there is a fallback to using the token (for example
                // importing data since a certain date), more importantly, the app should ensure
                // that the changes API is used sufficiently regularly that tokens do not expire.
                throw IOException("Changes token has expired")
            }
            emit(ChangesMessage.ChangeList(response.changes))
            nextChangesToken = response.nextChangesToken
        } while (response.hasMore)
        emit(ChangesMessage.NoMoreChanges(nextChangesToken))
    }
    suspend fun getChangesToken(): String {
        return healthConnectClient.getChangesToken(
            ChangesTokenRequest(
                setOf(
                    ExerciseSessionRecord::class,
                    StepsRecord::class,
                    TotalCaloriesBurnedRecord::class,
                    HeartRateRecord::class,
                    WeightRecord::class
                )
            )
        )
    }

    // Represents the two types of messages that can be sent in a Changes flow.
    sealed class ChangesMessage {
        data class NoMoreChanges(val nextChangesToken: String) : ChangesMessage()
        data class ChangeList(val changes: List<Change>) : ChangesMessage()
    }
}

/**
 * Health Connect requires that the underlying Health Connect APK is installed on the device.
 * [HealthConnectAvailability] represents whether this APK is indeed installed, whether it is not
 * installed but supported on the device, or whether the device is not supported (based on Android
 * version).
 */
enum class HealthConnectAvailability {
    INSTALLED,
    NOT_INSTALLED,
    NOT_SUPPORTED
}
