package com.mpdevelopment.hiltcoroutine

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    init {
        //TODO ADD TIMBER REPORTING / CRASHLYTICS / RELEASE TIMBER TREE
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}