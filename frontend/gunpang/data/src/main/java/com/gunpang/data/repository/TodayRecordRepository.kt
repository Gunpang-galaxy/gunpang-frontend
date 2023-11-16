package com.gunpang.data.repository

import android.util.Log
import com.gunpang.common.code.MealRecordCode
import com.gunpang.data.api.Api
import com.gunpang.data.api.TodayHistoryApi
import com.gunpang.data.model.request.FoodReqDto
import com.gunpang.data.model.request.SleepDataReqDto
import com.gunpang.data.model.response.TodayRecordResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class TodayRecordRepository (
    private val api : TodayHistoryApi =Api.getInstance().create(TodayHistoryApi::class.java)

){
    fun findTodayRecord(): Flow<TodayRecordResDto> = flow{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = ZonedDateTime.now()
                    .withZoneSameInstant(ZoneId.of("Asia/Seoul"))
                    .format(formatter)

        val response = api.watchTodayRecord(formattedDate)
        if(response.code()==200){
            response.body()?.let{
                emit(response.body()!!)
            }
        }
    }
    fun updateTodayRecord(foodType: MealRecordCode):Flow<Boolean> = flow{
        Log.d("TODAY_RECORD_REPO", "api 시작 전")
        val response = api.watchRecordFood(foodType)
        Log.d("TODAY_RECORD_REPO", response.toString())
        if(response.code()==201){
            emit(true)
        }else{
            emit(false)
        }

    }

    fun updateTodaySleepRecord(sleepDataReqDto: SleepDataReqDto):Flow<Boolean> = flow{
        Log.d("TODAY_SLEEP_RECORD_REPO", "api 시작 전")
        val response = api.watchRecordSleep(sleepDataReqDto)
        Log.d("TODAY_SLEEP_RECORD_REPO", response.toString())
        if(response.code()==201){
            emit(true)
        }else{
            emit(false)
        }

    }
}