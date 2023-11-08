package com.gunpang.domain.app.calendar

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.MealRecordCode
import com.gunpang.data.model.response.CalendarRecordResDto
import com.gunpang.data.repository.CalendarRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.gunpang.data.model.response.ExerciseResDto

class CalendarRecordViewModel: ViewModel() {
    var breakfastFoodType by mutableStateOf(MealRecordCode.NOT_RECORD) // 아침 HEALTHY, NORMAL, BAD, NOT_RECORD
    var lunchFoodType by mutableStateOf(MealRecordCode.NOT_RECORD) // 점심
    var dinnerFoodType by mutableStateOf(MealRecordCode.NOT_RECORD) // 저녁
    var exerciseTime by mutableStateOf("00시간 00분")
    var sleepAt by mutableStateOf("-1")
    var awakeAt by mutableStateOf("-1")
    var exercisesOnDate by mutableStateOf<List<ExerciseResDto>>(emptyList())

    private val calendarRecordRepository: CalendarRecordRepository = CalendarRecordRepository()

    fun init(){
        breakfastFoodType = MealRecordCode.NOT_RECORD // 아침 HEALTHY, NORMAL, BAD, NOT_RECORD
        lunchFoodType = MealRecordCode.NOT_RECORD // 점심
        dinnerFoodType =MealRecordCode.NOT_RECORD // 저녁
        exerciseTime = "00시간 00분"
        sleepAt = "-1"
        awakeAt = "-1"
        exercisesOnDate = emptyList()
    }

    fun requestApi(date : String) {
        viewModelScope.launch(Dispatchers.IO) {
            calendarRecordRepository.findRecordFromDate(date)
                .catch {
                    Log.d("CALENDAR_RECORD_VIEW_MODEL", it.printStackTrace().toString())
                }
                .collect { data ->
                    updateStates(data)
                    Log.d("calendar repo data",data.toString());
                }
        }
    }

    private fun updateStates(data: CalendarRecordResDto) {
        breakfastFoodType = data.breakfastFoodType
        lunchFoodType = data.lunchFoodType
        dinnerFoodType = data.dinnerFoodType
        exerciseTime = data.exerciseTime
        sleepAt = data.sleepAt
        awakeAt = data.awakeAt
        exercisesOnDate = data.exercisesOnDate
    }


}