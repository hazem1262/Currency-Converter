package com.hazem.androidmvistarter.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppInstance: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}