package com.example.productviewer

import android.app.Application
import com.example.productviewer.di.DaggerApplicationComponent

class MainApplication : Application() {
    val appComponent by lazy {
        DaggerApplicationComponent.builder().context(applicationContext).build()
    }
}