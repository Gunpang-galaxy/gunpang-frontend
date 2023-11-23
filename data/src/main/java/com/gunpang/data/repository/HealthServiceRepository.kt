package com.gunpang.data.repository

import android.util.Log
import androidx.health.services.client.ExerciseClient
import androidx.health.services.client.HealthServicesClient
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.ExerciseConfig
import androidx.health.services.client.data.ExerciseGoal
import androidx.health.services.client.data.ExerciseType
import androidx.health.services.client.data.ExerciseTypeCapabilities
import androidx.health.services.client.data.WarmUpConfig
import androidx.health.services.client.getCapabilities
import androidx.health.services.client.prepareExercise
import androidx.health.services.client.startExercise
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

class HealthServiceRepository @Inject constructor(
     healthServicesClient: HealthServicesClient
) {
    val exerciseClient: ExerciseClient = healthServicesClient.exerciseClient
     suspend fun prepareExercise() {
         Log.d("PREPARE_EXERCISE","Preparing an exercise")

         val warmUpConfig = WarmUpConfig(
             exerciseType = ExerciseType.RUNNING,
             dataTypes = setOf(DataType.HEART_RATE_BPM, DataType.LOCATION)
         )
         try {
             exerciseClient.prepareExercise(warmUpConfig)
         } catch (e: Exception) {
             Log.d("PREPARE_EXERCISE","Prepare exercise failed - ${e.message}")
         }
    }


    suspend fun startExercise() {
        Log.d("START_EXERCISE","Starting exercise")
        val capabilities = getExerciseCapabilities()

        if (capabilities == null) {
            Log.d("START_EXERCISE","No capabilities")
            return
        }

        val dataTypes = setOf(
            DataType.HEART_RATE_BPM,
            DataType.HEART_RATE_BPM_STATS,
            DataType.CALORIES_TOTAL,
            DataType.DISTANCE_TOTAL,
        ).intersect(capabilities.supportedDataTypes)
        val exerciseGoals = mutableListOf<ExerciseGoal<Double>>()

        val config = ExerciseConfig(
            exerciseType = ExerciseType.RUNNING,
            dataTypes = dataTypes,
            isAutoPauseAndResumeEnabled = false,
            isGpsEnabled = true,
            exerciseGoals = exerciseGoals
        )

        exerciseClient.startExercise(config)
        Log.d("START_EXERCISE","Started exercise")
    }


    suspend fun getExerciseCapabilities(): ExerciseTypeCapabilities? {
        val capabilities = exerciseClient.getCapabilities()

        return if (ExerciseType.RUNNING in capabilities.supportedExerciseTypes) {
            capabilities.getExerciseTypeCapabilities(ExerciseType.RUNNING)
        } else {
            null
        }
    }
}

