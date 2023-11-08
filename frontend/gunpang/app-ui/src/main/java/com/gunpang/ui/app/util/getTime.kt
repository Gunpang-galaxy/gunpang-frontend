package com.gunpang.ui.app.util

fun getTimeFromString(time : String) : String{
    var timeSplit: List<String> = time.split(" ")
    if(timeSplit[0] == "00시간")
        return timeSplit[1]
    return time
}