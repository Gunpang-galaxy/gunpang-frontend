package com.gunpang.domain.watch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.wear.remote.interactions.RemoteActivityHelper
import com.google.android.gms.wearable.CapabilityClient

class WatchLandingViewModel(
    private var capabilityClient: CapabilityClient,
    private var remoteActivityHelper: RemoteActivityHelper,
    application: Application
)  : AndroidViewModel(application){
    companion object {
        private const val START_APP_ACTIVITY_PATH = "/start-activity"
        private const val CAPABILITY_PHONE_APP = "app_gunpang"
        //private const val ANDROID_MARKET_APP_URI = "market://details?id=com.gunpang"
    }

}

class WatchLandingViewModelFactory(
    private var capabilityClient: CapabilityClient,
    private val remoteActivityHelper: RemoteActivityHelper,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WatchLandingViewModel::class.java)){
            return WatchLandingViewModel(capabilityClient, remoteActivityHelper, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}