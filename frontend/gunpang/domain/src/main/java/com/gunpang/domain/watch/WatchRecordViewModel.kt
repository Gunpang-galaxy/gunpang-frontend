package com.gunpang.domain.watch

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.MealRecordCode
import com.gunpang.data.model.request.FoodReqDto
import com.gunpang.data.model.response.TodayRecordResDto
import com.gunpang.data.repository.TodayRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WatchRecordViewModel(
    application: Application
) : AndroidViewModel(application) {

    var breakfastFoodType by mutableStateOf(MealRecordCode.NOT_RECORD) // 아침 HEALTH, MEDIUM, UNHEALTH
    var lunchFoodType by mutableStateOf(MealRecordCode.HEALTHY) // 점심
    var dinnerFoodType by mutableStateOf(MealRecordCode.BAD) // 저녁
    var exerciseTime by mutableStateOf("00시간 00분")
    var sleepTime by mutableStateOf("11시간 11분")

    private val todayRecordRepository: TodayRecordRepository = TodayRecordRepository()

    fun init() {
        viewModelScope.launch(Dispatchers.IO)  {
            todayRecordRepository.findTodayRecord()
                .catch {
                    Log.d("WATCHRECORD_VIEW_MODEL",it.printStackTrace().toString())
                }
                .collect{data->
                    updateStates(data)
                }
        }
    }
    private fun updateStates(data: TodayRecordResDto) {
        breakfastFoodType  = data.breakfastFoodType
        lunchFoodType = data.lunchFoodType
        dinnerFoodType  = data.dinnerFoodType
        exerciseTime = data.exerciseTime
        sleepTime  = data.sleepTime
    }




}