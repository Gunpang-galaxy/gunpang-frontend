package com.gunpang.domain.watch.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.repository.HealthServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PrepareExerciseViewModel @Inject constructor(
    private val healthServiceRepository:
    HealthServiceRepository,
) : ViewModel()  {

    init {
        viewModelScope.launch {
            healthServiceRepository.prepareExercise()
        }
    }

    suspend fun startExercise() {
        healthServiceRepository.startExercise()
    }
}


