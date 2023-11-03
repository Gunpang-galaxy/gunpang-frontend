package com.gunpang.domain.watch

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gunpang.data.repository.HealthServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val healthServicesRepository: HealthServicesRepository
) :  ViewModel() {


    suspend fun isExerciseInProgress(): Boolean {
        return healthServicesRepository.isExerciseInProgress()
    }

    fun startExercise() {
        Log.d("START_EXERCISE","start exercise")
        healthServicesRepository.startExercise()
    }

    fun pauseExercise() {
        healthServicesRepository.pauseExercise()
    }

    fun endExercise() {
        healthServicesRepository.endExercise()
    }

    fun resumeExercise() {
        healthServicesRepository.resumeExercise()
    }
}