package com.gunpang.domain.watch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gunpang.data.repository.TodayRecordRepository

class TodayHistoryViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val todayRecordRepository: TodayRecordRepository = TodayRecordRepository()

}