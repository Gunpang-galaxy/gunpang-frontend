package com.gunpang.data.repository

import com.gunpang.data.api.Api
import com.gunpang.data.api.BodyCompositionApi
import com.gunpang.data.model.response.BodyCompositionInfoResDto
import android.util.Log
import com.gunpang.data.model.request.BodyCompositionApiReqDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import kotlin.jvm.Throws


class BodyCompositionRepository (

    private val api: BodyCompositionApi = Api.getInstance().create(BodyCompositionApi::class.java)
) {

    @Throws(IOException::class)
    fun getBodyCompositionInfo() : Flow<BodyCompositionInfoResDto> = flow {
        val response = api.bodyCompositionInfo()
        if(response.code() == 200) {
            response.body()?.let{
                emit(response.body()!!)
            }
        }
    }

    @Throws(IOException::class)
    fun registerBodyComposition(bodyCompositionApiReqDto: BodyCompositionApiReqDto): Flow<Unit> = flow {
        Log.d("bodyCompositionApiReqDto",bodyCompositionApiReqDto.toString())

        val response = api.registerBodyComposition(bodyCompositionApiReqDto)
        if (response.code() == 201){
            Log.d("[registerBodyComposition]", "registerBodyCompositionSuccess")
        } else {
            Log.d("registerBodyCompositionErrorBody",response.errorBody().toString())
            Log.d("registerBodyCompositionMessage",response.message().toString())
            Log.d("registerBodyCompositionCode",response.code().toString())
            Log.d("[registerBodyComposition]", "registerBodyCompositionFail")
        }
    }
}

