package com.gunpang.domain.watch


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.MealRecordCode
import com.gunpang.common.code.TimeToEatCode
import com.gunpang.data.model.request.FoodReqDto
import com.gunpang.data.repository.TodayRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WatchFeedViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val todayRecordRepository: TodayRecordRepository = TodayRecordRepository()

    fun feedFood(foodType: MealRecordCode){
        Log.d("WATCH_FEED_VIEW_MODEL",foodType.toString());
//        viewModelScope.launch (Dispatchers.IO){
//            todayRecordRepository.updateTodayRecord(FoodReqDto(foodType))
//                .catch {
//                    Log.d("WATCHRECORD_VIEW_MODEL",it.printStackTrace().toString())
//                }
//        }
    }

}