package com.ekdev.habitapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HabitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}