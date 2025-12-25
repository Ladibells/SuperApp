package dev.ladibells.superapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.ladibells.utilities.logging.AppLogger

@HiltAndroidApp
class SuperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(message = "Application is launched")
    }

    companion object {
        const val TAG = "SuperApplication"
    }
}