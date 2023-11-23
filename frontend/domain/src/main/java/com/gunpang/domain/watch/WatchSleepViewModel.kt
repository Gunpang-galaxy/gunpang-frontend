package com.gunpang.domain.watch

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.MealRecordCode
import com.gunpang.common.code.TimeToEatCode
import com.gunpang.data.model.request.FoodReqDto
import com.gunpang.data.model.request.SleepDataReqDto
import com.gunpang.data.repository.TodayRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WatchSleepViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val todayRecordRepository: TodayRecordRepository = TodayRecordRepository()

    //수면 api 보고 바꿔야됨
    fun recordSleep(sleepDataReqDto: SleepDataReqDto){
        var isRecorded = true
        Log.d("WATCH_SLEEP_VIEW_MODEL",sleepDataReqDto.toString());
        viewModelScope.launch (Dispatchers.IO){
            todayRecordRepository.updateTodaySleepRecord(sleepDataReqDto)
                .catch {
                    it.printStackTrace()
                    Log.d("WATCH_RECORD_VIEW_MODEL","something wrong")
                }.collect{
                    isRecorded = it
                    Log.d("WATCH_RECORD_VIEW_MODEL","right")
                }
        }
        Log.d("WATCH_RECORD_VIEW_MODEL", isRecorded.toString())
    }

}