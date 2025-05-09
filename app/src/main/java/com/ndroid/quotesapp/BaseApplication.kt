package com.ndroid.quotesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Nityen on 08-05-2025.
 */

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}