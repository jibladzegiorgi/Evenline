package com.example.evenline

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EvenlineApplication: Application() {


    override fun onCreate() {
        super.onCreate()
    }

}