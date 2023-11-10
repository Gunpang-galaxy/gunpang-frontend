package com.gunpang.data.repository;

import com.gunpang.data.DataApplication
class DataApplicationRepository {
    fun getValue(key: String)  : String {
        return DataApplication.prefs.getString(key, "").toString()
    }
    fun setValue(key: String, value: String) {
        DataApplication.prefs.setString(key,value)
    }
    fun removeValue(key: String) {
        DataApplication.prefs.removeString(key)
    }
}
