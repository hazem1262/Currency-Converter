package com.hazem.currency_converter.application

import android.app.Application
import com.hazem.currency_converter.BuildConfig
import com.hazem.currency_converter.utils.log.ReleaseTree
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppInstance: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())

            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    Logger.log(priority, tag, message, t)
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}