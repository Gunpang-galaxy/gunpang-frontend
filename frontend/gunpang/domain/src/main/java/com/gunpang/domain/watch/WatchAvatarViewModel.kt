package com.gunpang.domain.watch

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.AvatarCode
import com.gunpang.data.model.response.WatchCurrentAvatarResDto
import com.gunpang.data.repository.AvatarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WatchAvatarViewModel (
    application: Application
): AndroidViewModel(application) {

    var avatarTypeId by mutableStateOf(AvatarCode.AVATAR_CAT)
    var status by mutableStateOf("")
    var healthPoint by mutableStateOf(0.5f)
    var stage by mutableStateOf("GROUND")

    private val avatarRepository : AvatarRepository = AvatarRepository()
    fun init() {

        viewModelScope.launch(Dispatchers.IO) {
            avatarRepository.findWatchCurrentAvatar()
                .catch {
                    Log.d("AVATAR_VIEW_MODEL",it.printStackTrace().toString())
                }
                .collect{data->
                   updateStates(data)

                }

        }
    }

    private fun updateStates(data: WatchCurrentAvatarResDto) {
        avatarTypeId = data.avatarTypeId
        status = data.status.toString()
        healthPoint = data.healthPoint;
        stage = data.stage
    }


}