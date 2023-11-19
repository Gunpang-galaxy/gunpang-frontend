package com.gunpang.domain.app.healthconnect

import android.os.RemoteException
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.BodyFatRecord
import androidx.health.connect.client.records.BodyWaterMassRecord
import androidx.health.connect.client.records.BoneMassRecord
import androidx.health.connect.client.records.SleepSessionRecord
import androidx.health.connect.client.records.WeightRecord
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gunpang.data.manager.AppHealthConnectManager
import com.gunpang.data.model.request.BodyCompositionApiReqDto
import com.gunpang.data.model.request.SleepHealthConnectReqDto
import com.gunpang.data.repository.BodyCompositionRepository
import com.gunpang.data.repository.TodayRecordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.Instant
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID

class AppHealthViewModel(
    private val appHealthConnectManager: AppHealthConnectManager
) : ViewModel(){
    val permissions = setOf(
        HealthPermission.getReadPermission(SleepSessionRecord::class),
        HealthPermission.getReadPermission(WeightRecord::class),
        HealthPermission.getReadPermission(BodyFatRecord::class),
//        HealthPermission.getReadPermission(BodyWaterMassRecord::class),
//        HealthPermission.getReadPermission(BoneMassRecord::class)
    )

    // 체중 정보 list
    var readingWeight: MutableState<Double>  = mutableDoubleStateOf(0.0)
        private set

    // 수면 정보 list
    var readingSleepList: MutableState<List<Instant>>  = mutableStateOf(listOf())
        private set

    // 체지방량 정보 list
    var readingBodyFat: MutableState<Double> = mutableDoubleStateOf(0.0)
        private set

//    // 체수분량 정보 list
//    var readingBodyWaterMassList: MutableState<List<Double>> = mutableStateOf(listOf())
//        private set
//
//    // 골격근량 정보 list
//    var readingBoneMassList: MutableState<List<Double>> = mutableStateOf(listOf())
//        private set

    var uiState: UiState by mutableStateOf(UiState.Uninitialized)
        private set

    // 권한 부여 상태
    var permissionGranted = mutableStateOf(false)
        private set
    //
    val permissionLauncher = appHealthConnectManager.requestPermissionsActivityContract()

    fun initialLoad() {
        viewModelScope.launch {
            tryWithPermissionsCheck {
                readAssociatedSessionData()
            }
        }
    }
    private suspend fun readAssociatedSessionData() {
        //readSleepInputs()
        registerBodyComposition()
        registerSleepByHealthConnect()
//        readBodyFatInput()
//        readWeightInput()
//        readBodyWaterMassInput()
//        readBoneMassInput()
    }
    private suspend fun readSleepInputs(): List<Instant> {
        val yesterdayStartOfDay = ZonedDateTime.now().minusDays(1).truncatedTo(ChronoUnit.DAYS)
        val now = Instant.now()
        //val endOfWeek = startOfDay.toInstant().plus(7, ChronoUnit.DAYS)
        //이거 기간 조정해서 써보기
        readingSleepList.value = appHealthConnectManager.readSleepInputs(yesterdayStartOfDay.toInstant(), now)
        Log.d("[reading Sleep List]",
            "${readingSleepList.value.forEach { it -> it.toString()}}")
        return readingSleepList.value
    }

    private suspend fun readBodyFatInput(): Double {
        val oneWeekAgoStartOfDay = ZonedDateTime.now().minusWeeks(1).truncatedTo(ChronoUnit.DAYS)
        val now = Instant.now()
//        val endOfWeek = startOfDay.toInstant().plus(7, ChronoUnit.DAYS)
        readingBodyFat.value = appHealthConnectManager.readBodyFatInput(oneWeekAgoStartOfDay.toInstant(), now)
        Log.d("[reading bodyfat]",
            "${readingBodyFat.value.toString()}")
        return readingBodyFat.value
    }

    private suspend fun readWeightInput(): Double {
        val oneWeekAgoStartOfDay = ZonedDateTime.now().minusWeeks(1).truncatedTo(ChronoUnit.DAYS)
        val now = Instant.now()
//        val endOfWeek = startOfDay.toInstant().plus(7, ChronoUnit.DAYS)
        readingWeight.value =
            appHealthConnectManager.readWeightInputs(oneWeekAgoStartOfDay.toInstant(), now)
        Log.d("[reading Weights List]","${readingWeight.value.toString()}")
//            "${readingWeight.value.forEach { it -> it.toString() }}")
        return readingWeight.value
    }

//    private suspend fun readBodyWaterMassInput(){
//        val oneYearAgoStartOfDay = ZonedDateTime.now().minusYears(1).truncatedTo(ChronoUnit.DAYS)
//        val now = Instant.now()
////        val endOfWeek = startOfDay.toInstant().plus(7, ChronoUnit.DAYS)
////        readingBodyWaterMassList.value = appHealthConnectManager.readBodyWaterMassInput(oneYearAgoStartOfDay.toInstant(), now)
//        readingBodyWaterMassList.value = listOf(appHealthConnectManager.readBodyWaterMassInput(oneYearAgoStartOfDay.toInstant(), now))
//        Log.d("[reading BodyWaterMass List]",
//            "${readingBodyWaterMassList.value.forEach { it -> it.toString() }}")
//
//    }

//    private suspend fun readBoneMassInput(){
//        val oneYearAgoStartOfDay = ZonedDateTime.now().minusYears(1).truncatedTo(ChronoUnit.DAYS)
//        val now = Instant.now()
////        val endOfWeek = startOfDay.toInstant().plus(7, ChronoUnit.DAYS)
//        readingBoneMassList.value = listOf(appHealthConnectManager.readBoneMassInput(oneYearAgoStartOfDay.toInstant(), now))
//        Log.d("[reading BoneMass List]",
//            "${readingBoneMassList.value.forEach { it -> it.toString() }}")
//
//    }

    private val bodyCompositionRepository = BodyCompositionRepository()
    private val todayRecordRepository = TodayRecordRepository()

    suspend fun registerBodyComposition() {
        val fatMassPct: Double = readBodyFatInput()
        val weight: Double = readWeightInput()
        val bodyCompositionApiReqDto = BodyCompositionApiReqDto(fatMassPct = fatMassPct,weight= weight)
        Log.d("registerBodyComposition","registerBodyComposition");
        viewModelScope.launch {
            bodyCompositionRepository.registerBodyComposition(bodyCompositionApiReqDto = bodyCompositionApiReqDto)
                .catch {  }
                .collect {
                }
        }
    }

    suspend fun registerSleepByHealthConnect() {
        if(readSleepInputs().size <= 1) return
        val sleepAt: String = readSleepInputs().get(0).plus(9, ChronoUnit.HOURS).toString()
        val awakeAt: String =readSleepInputs().get(1).plus(9, ChronoUnit.HOURS).toString()

//        val recordDate: String = Instant.now().plus(9, ChronoUnit.HOURS).toString()
        val recordDate: String = LocalDate.now().toString()
        val sleepHealthConnectReqDto = SleepHealthConnectReqDto(sleepAt=sleepAt,awakeAt=awakeAt, recordDate = recordDate)
        Log.d("registerSleepByHealthConnect","registerBodyComposition");
        viewModelScope.launch {
            todayRecordRepository.registerSleepByHealthConnect(sleepHealthConnectReqDto = sleepHealthConnectReqDto)
                .catch {  }
                .collect {
                }
        }
    }
    private suspend fun tryWithPermissionsCheck(block : suspend() -> Unit){
        // TODO : Permission Granted value로 확인
        Log.d("TRY_WITH_PERMISSIONS","DDDDDDDDDDDDDDDDDDDDD");
        permissionGranted.value = appHealthConnectManager.hasAllPermissions(permissions)
        uiState= try {
            if (permissionGranted.value) {
                Log.d("TRY_WITH_PERMISSIONS", permissionGranted.value.toString())
                block()
            }
            UiState.Done
        } catch (remoteException: RemoteException) {
            UiState.Error(remoteException)
        } catch (securityException: SecurityException) {
            UiState.Error(securityException)
        } catch (ioException: IOException) {
            UiState.Error(ioException)
        } catch (illegalStateException: IllegalStateException) {
            UiState.Error(illegalStateException)
        }
    }
    sealed class UiState {
        object Uninitialized : UiState()
        object Done : UiState()

        // A random UUID is used in each Error object to allow errors to be uniquely identified,
        // and recomposition won't result in multiple snackbars.
        data class Error(val exception: Throwable, val uuid: UUID = UUID.randomUUID()) : UiState()
    }
}

class AppHealthViewModelFactory(
    private val appHealthConnectManager: AppHealthConnectManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppHealthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppHealthViewModel(
                appHealthConnectManager = appHealthConnectManager
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}