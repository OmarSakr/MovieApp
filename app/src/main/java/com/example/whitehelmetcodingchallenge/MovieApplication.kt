package com.example.whitehelmetcodingchallenge

import android.app.Application
import com.example.whitehelmetcodingchallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@MovieApplication)
            modules(appModule)
        }
    }
}
