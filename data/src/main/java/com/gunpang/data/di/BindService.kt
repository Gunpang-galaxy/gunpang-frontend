package com.gunpang.data.di

import android.app.Service
import android.content.Context
import android.os.IBinder
import dagger.hilt.android.ActivityRetainedLifecycle
import com.google.android.horologist.health.service.BinderConnection
inline fun <reified T : IBinder, reified S : Service> ActivityRetainedLifecycle.bindService(context: Context): BinderConnection<T> {
    val connection = BinderConnection(context, T::class)
    BinderConnection.bindService(context, S::class, connection)
    addOnClearedListener {
        connection.unbind()
    }
    return connection
}