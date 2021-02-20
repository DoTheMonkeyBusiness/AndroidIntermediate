package com.nasalevich.androidintermidiate

import android.app.Application
import com.nasalevich.androidintermidiate.koin.appModule
import com.nasalevich.androidintermidiate.pages.mainPage.koin.mainModule
import com.nasalevich.androidintermidiate.pages.tinderPage.koin.tinderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArchApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ArchApplication)
            modules(
                appModule,
                mainModule,
                tinderModule,
            )
        }
    }
}
