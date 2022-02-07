package com.timife.teesast.common.di

import android.app.Application
import com.bumptech.glide.Glide.init
import dagger.hilt.android.HiltAndroidApp
import okhttp3.logging.HttpLoggingInterceptor
import java.util.logging.Logger

@HiltAndroidApp
class TeesasApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
//       Logger.init()
    }
}