package com.nasalevich.androidintermidiate

import android.app.Application
import com.nasalevich.androidintermidiate.mainPage.koin.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@ArchApplication)
            modules(mainModule)
        }
    }
}
