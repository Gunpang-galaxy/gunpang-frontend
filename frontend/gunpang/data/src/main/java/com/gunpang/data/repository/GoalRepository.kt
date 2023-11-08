package com.gunpang.data.repository

import android.util.Log
import com.gunpang.data.api.Api
import com.gunpang.data.api.GoalApi
import com.gunpang.data.model.request.SleepGoalReqDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GoalRepository(
    private val api: GoalApi = Api.getInstance().create(GoalApi::class.java)
) {
    fun setAvatarGoal(
        startTime: Int,
        startMinute: Int,
        endTime: Int,
        endMinute: Int,
        exerciseDay: Int,
        exerciseTime: Int
    ) : Flow<Boolean> = flow{
        val response = api.setGoal(
            sleepGoalReqDto = SleepGoalReqDto(
                startTime = startTime,
                startMinute = startMinute,
                endTime = endTime,
                endMinute = endMinute,
                exerciseDay = exerciseDay,
                exerciseTime = exerciseTime
            ))
        if(response.code() == 200){
            response.body()?.let{
                emit(true)
            }
        }
        else  {
            emit(false)
        }
    }
}