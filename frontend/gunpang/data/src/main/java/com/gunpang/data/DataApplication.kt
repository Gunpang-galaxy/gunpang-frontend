package com.gunpang.data

import android.app.Application

open class DataApplication : Application(){ // 앱의 생명 주기 동안 유지되는 전역 변수나 상태를 관리하기 위해 사용
    companion object {
        lateinit var prefs: GunpangPreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        //Log.d("DATA_APPLICATION","ON CREATE!!!!!!!!!!!!!!!!!")
        prefs = GunpangPreferenceUtil(applicationContext)
    }
}