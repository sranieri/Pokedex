package com.sample.pokedex

import android.app.Application
import com.sample.pokedex.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, dataSourceModule, repoModule, useCaseModule, viewModelModule))
        }
    }
}