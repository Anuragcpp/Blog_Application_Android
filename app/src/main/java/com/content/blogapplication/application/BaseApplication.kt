package com.content.blogapplication.application

import android.app.Application
import android.content.Context
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    companion object {
        lateinit var mContext : Context
           private set
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}