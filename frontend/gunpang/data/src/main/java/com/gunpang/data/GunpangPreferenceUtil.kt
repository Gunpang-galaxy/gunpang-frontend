package com.gunpang.data

import android.content.Context
import android.content.SharedPreferences

class GunpangPreferenceUtil(context:Context) { //사용자의 설정이나 애플리케이션의 상태를 저장하는데 사용되는 유틸리티 클래스
    private val prefs: SharedPreferences=
        context.getSharedPreferences("shared_pref",Context.MODE_PRIVATE)

    fun getString(key:String, defValue: String):String{
        return prefs.getString(key, defValue).toString()
    }
    fun setString(key:String, str:String){
        prefs.edit().putString(key,str).apply()
    }

    fun removeString(key: String) {
        prefs.edit().remove(key).apply()
    }
}