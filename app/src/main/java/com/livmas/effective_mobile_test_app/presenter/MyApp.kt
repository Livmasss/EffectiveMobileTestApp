package com.livmas.effective_mobile_test_app.presenter

import android.app.Application
import com.livmas.data.dataModule
import com.livmas.effective_mobile_test_app.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            androidLogger()
            modules(listOf(appModule, dataModule))
        }
    }
}