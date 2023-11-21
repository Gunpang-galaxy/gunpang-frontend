package com.gunpang.ui.app.util

import android.util.Log

fun getTimeFromString(time : String) : String{
    val timeSplit: List<String> = time.split(" ")
    Log.d("[time split]", timeSplit.toString())
    if(time =="00시간00분")
        return ""
    if(timeSplit[0] == "00시간")
        return timeSplit[1]
    if(timeSplit[1] == "00분")
        return timeSplit[0]
    return time
}