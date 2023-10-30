package com.gunpang.domain.watch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AvatarViewModel (
    application: Application
): AndroidViewModel(application) {

    fun init() {

        viewModelScope.launch(Dispatchers.Main) {
            // findAvatar()
        }
    }


}