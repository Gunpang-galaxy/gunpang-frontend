package com.gunpang.app

import com.gunpang.data.manager.AppHealthConnectManager
import com.gunpang.data.DataApplication

open class AppDataApplication : DataApplication() {
    val healthConnectManager by lazy {
        AppHealthConnectManager(this)
    }
}