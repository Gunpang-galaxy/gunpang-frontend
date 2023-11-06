package com.gunpang.data.repository

import android.content.Context
import android.util.Log
import androidx.health.services.client.data.LocationAvailability
import com.gunpang.data.service.ExerciseService
import com.example.exercisesamplecompose.service.ExerciseServiceState
import com.gunpang.data.manager.ExerciseClientManager
import com.gunpang.data.manager.isExerciseInProgress
import com.gunpang.data.manager.isTrackingExerciseInAnotherApp
import dagger.hilt.android.ActivityRetainedLifecycle
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityRetainedScoped
class HealthServicesRepository @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    val exerciseClientManager: ExerciseClientManager,
    val coroutineScope: CoroutineScope,
    val lifecycle: ActivityRetainedLifecycle
) {

    suspend fun hasExerciseCapability(): Boolean = getExerciseCapabilities() != null

    private suspend fun getExerciseCapabilities() = exerciseClientManager.getExerciseCapabilities()

    suspend fun isExerciseInProgress(): Boolean =
        exerciseClientManager.exerciseClient.isExerciseInProgress()

    suspend fun isTrackingExerciseInAnotherApp(): Boolean =
        exerciseClientManager.exerciseClient.isTrackingExerciseInAnotherApp()

    fun prepareExercise() {
        Log.d("PREPARE_EXERCISE","prepareExercise in ")
        coroutineScope.launch {
            exerciseClientManager.prepareExercise()
        }
       }

    fun startExercise() {
        Log.d("START_EXERCISE","startExercise in ")

        coroutineScope.launch {
            exerciseClientManager.startExercise()
        }
    }

    fun pauseExercise(){
        coroutineScope.launch {
            exerciseClientManager.pauseExercise()
        }
    }

    fun endExercise() {
        coroutineScope.launch {
            exerciseClientManager.endExercise()
        }
    }

    fun resumeExercise(){
        coroutineScope.launch {
            exerciseClientManager.resumeExercise()
        }
    }
}

/** Store exercise values in the service state. While the service is connected,
 * the values will persist.**/
sealed class ServiceState {
    object Disconnected : ServiceState()
    data class Connected(
        val exerciseServiceState: ExerciseServiceState,
    ) : ServiceState() {
        val locationAvailabilityState: LocationAvailability =
            exerciseServiceState.locationAvailability
    }
}



