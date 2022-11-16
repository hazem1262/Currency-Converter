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

        /*
        * use timber to log events in logcat while in debug mode and the ability to log to crashyltics
        * analytics or any other tool while being in release mode
        * */
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