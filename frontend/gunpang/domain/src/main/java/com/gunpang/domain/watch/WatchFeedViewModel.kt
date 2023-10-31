package com.gunpang.domain.watch


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.model.request.FoodReqDto
import com.gunpang.data.repository.TodayRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WatchFeedViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val todayRecordRepository: TodayRecordRepository = TodayRecordRepository()

    fun feedFood(foodReqDto:FoodReqDto){
        viewModelScope.launch (Dispatchers.IO){
            todayRecordRepository.updateTodayRecord(foodReqDto)
                .catch {
                    Log.d("WATCHRECORD_VIEW_MODEL",it.printStackTrace().toString())
                }
        }
    }

}