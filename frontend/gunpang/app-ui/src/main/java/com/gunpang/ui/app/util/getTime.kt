package com.gunpang.ui.app.util

fun getTimeFromString(time : String) : String{
    val timeSplit: List<String> = time.split(" ")
    if(time =="00시간00분")
        return "운동 안했네.."
    if(timeSplit[0] == "00시간")
        return timeSplit[1]
    if(timeSplit[1] == "00분")
        return timeSplit[0]
    return time
}