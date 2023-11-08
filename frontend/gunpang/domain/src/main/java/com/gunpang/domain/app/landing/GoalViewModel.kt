package com.gunpang.domain.app.landing

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.repository.GoalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GoalViewModel : ViewModel()  {
    private val goalRepository = GoalRepository()

    private var sleepStartTime: Int = 0
    private var sleepStartMinute: Int = 0
    private var sleepEndTime: Int = 0
    private var sleepEndMinute: Int = 0
    private var exerciseDay: Int = 0
    private var exerciseTime: Int = 0


    // 목표 정보 서버로 보내기
    fun setAvatarGoal() : Boolean {
        var result = true
        Log.d("[Goal View Model]", "call SetAvatarGoal :" +
                "자는 시간 $sleepStartTime : $sleepStartMinute " +
                "기상 시간 $sleepEndTime : $sleepEndMinute " +
                "운동 날짜 $exerciseDay " +
                "운동 시간$exerciseTime ")
        viewModelScope.launch(Dispatchers.IO) {
            goalRepository.setAvatarGoal(
                startTime = sleepStartTime,
                startMinute = sleepStartMinute,
                endTime = sleepEndTime,
                endMinute = sleepEndMinute,
                exerciseDay = exerciseDay,
                exerciseTime = exerciseTime
            ).catch {
                it.printStackTrace()
                result = false
            }.collect{
                data ->
                result = data
            }
        }
        Log.d("[Goal View Model]", "result : $result")
        return result
    }

    // 수면 목표 viewModel에 저장
    fun setSleepGoal(
        startHour : String,
        startMinute: String,
        endHour: String,
        endMinute: String
    ){
        this.sleepStartTime = startHour.toInt()
        this.sleepStartMinute = startMinute.toInt()
        this.sleepEndTime = endHour.toInt()
        this.sleepEndMinute = endMinute.toInt()
    }

    fun setExerciseGoal(
        selectedDay: Int,
        minute: String
    ){
        this.exerciseDay = selectedDay
        this.exerciseTime = minute.toInt()
    }
}