package com.gunpang.domain.app.bodyComposition

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.data.model.response.BodyCompositionInfoResDto
import com.gunpang.data.repository.BodyCompositionRepository
import com.gunpang.domain.entity.BodyCompositionInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * BodyComposition 정보를 위한 ViewModel
 */
class BodyCompositionViewModel : ViewModel() {
//    var bodyCompositionInfo by mutableStateOf<BodyCompositionInfo>(BodyCompositionInfo())
    var prevWeight by mutableStateOf("0.0")
    var curWeight by mutableStateOf("0.0")
    var prevFatMass by mutableStateOf("0.0")
    var curFatMass by mutableStateOf("0.0")
    var prevFatMassPct by mutableStateOf("0.0")
    var curFatMassPct by mutableStateOf("0.0")
    var prevBMI by mutableStateOf("0.0")
    var curBMI by mutableStateOf("0.0")
    private var bodyCompositionRepository: BodyCompositionRepository = BodyCompositionRepository()

    fun init() {
        Log.d("BodyCompositionViewModel", "init")
        prevWeight = "0.0"
        curWeight = "0.0"
        prevFatMass = "0.0"
        curFatMass = "0.0"
        curFatMassPct = "0.0"
        prevFatMassPct = "0.0"
        prevBMI = "0.0"
        curBMI  = "0.0"

        bodyCompositionInfo()
    }

    private fun bodyCompositionInfo(){
        viewModelScope.launch(Dispatchers.IO){
            bodyCompositionRepository.getBodyCompositionInfo()
                .catch{
                    Log.d("BodyCompositionViewModel", it.printStackTrace().toString())
                }
                .collect{data ->
                    updateStates(data)
                    Log.d("BodyCompositionViewModel2",data.toString());
                }
        }

    }

    private fun updateStates(data: BodyCompositionInfoResDto) {
        prevWeight = data.prevWeight
        curWeight = data.curWeight
        prevFatMass = data.prevFatMass
        curFatMass = data.curFatMass
        curFatMassPct = data.curFatMassPct
        prevFatMassPct = data.prevFatMassPct
        prevBMI = data.prevBMI
        curBMI  = data.curBMI
    }

//    fun init(){
//        bodyCompositionInfo()
//    }


}