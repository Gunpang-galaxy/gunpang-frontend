package com.gunpang.data.service

import java.time.Duration

data class SummaryScreenState(
    val averageHeartRate: Double,
    val totalDistance: Double,
    val totalCalories: Double,
    val elapsedTime: Duration,
)