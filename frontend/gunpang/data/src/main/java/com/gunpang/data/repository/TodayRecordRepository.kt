package com.gunpang.data.repository

import com.gunpang.data.api.Api
import com.gunpang.data.api.TodayHistoryApi
import com.gunpang.data.model.response.TodayRecordResDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class TodayRecordRepository (
    private val api : TodayHistoryApi =Api.getInstance().create(TodayHistoryApi::class.java)

){
    fun findTodayHistory(): Flow<TodayRecordResDto> = flow{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = ZonedDateTime.now()
                    .withZoneSameInstant(ZoneId.of("Asia/Seoul"))
                    .format(formatter)

        val response = api.todayRecord(formattedDate)
        if(response.code()==200){
            response.body()?.let{
                emit(response.body()!!)
            }
        }
    }
}