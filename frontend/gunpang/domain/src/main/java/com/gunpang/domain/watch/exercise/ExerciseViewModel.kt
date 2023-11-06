package com.gunpang.domain.watch.exercise

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.repository.HealthServicesRepository
import com.gunpang.data.repository.ServiceState
import com.gunpang.data.service.ExerciseScreenState
import com.gunpang.data.service.SummaryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.Duration
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val healthServicesRepository: HealthServicesRepository
) : ViewModel() {
    var elapsedTime: Duration? = Duration.ZERO
    val uiState: StateFlow<ExerciseScreenState> = healthServicesRepository.serviceState.map {
        ExerciseScreenState(
            hasExerciseCapabilities = healthServicesRepository.hasExerciseCapability(),
            isTrackingAnotherExercise = healthServicesRepository.isTrackingExerciseInAnotherApp(),
            serviceState = it,
            exerciseState = (it as? ServiceState.Connected)?.exerciseServiceState
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3_000),
        healthServicesRepository.serviceState.value.let {
            ExerciseScreenState(
                true,
                false,
                it,
                (it as? ServiceState.Connected)?.exerciseServiceState
            )
        }

    )

    suspend fun isExerciseInProgress(): Boolean {
        return healthServicesRepository.isExerciseInProgress()
    }

    fun startExercise() {
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
