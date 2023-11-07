package com.gunpang.domain.app.avatar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunpang.common.code.AvatarCode
import com.gunpang.data.model.request.NameAvatarReqDto
import com.gunpang.data.repository.AvatarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewAvatarViewModel: ViewModel() {

    private val avatarRepository : AvatarRepository = AvatarRepository() // 아바타 관련 Repository

    // 새로운 아바타 뽑기
    fun registerRandomAvatar(
        avatarType: AvatarCode?,
        name: String
    ) {
        val avatarTypeString = AvatarCode.values().find { it.imageId == avatarType!!.imageId }.toString()
        viewModelScope.launch {
            val nameAvatarReq = NameAvatarReqDto(avatarTypeString, name)
            avatarRepository.registerRandomAvatar(nameAvatarReq)
                .catch {
                    Log.d("AVATAR_VIEW_MODEL", "register new avatar failed")
                    it.printStackTrace()
                }
                .collect { data ->
                }
        }
    }

}