package com.gunpang.data.repository;

import com.gunpang.data.GunpangDataApplication
class GunpangDataApplicationRepository {
    fun getValue(key: String) {
        return GunpangDataApplication.prefs.getString(key,"")
    }
    fun setValue(key: String, value: String) {
        GunpangDataApplication.prefs.setString(key,value)
    }
}
