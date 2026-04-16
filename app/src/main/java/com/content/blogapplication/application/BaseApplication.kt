package com.content.blogapplication.application

import android.app.Application
import android.content.Context

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