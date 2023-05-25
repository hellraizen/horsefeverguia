package com.dleite.horsefeverguia

import android.app.Application
import com.dleite.horsefeverguia.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    daoModule,
                    uiModule,
                    viewModelModule,
                    firebaseModule,
                    userCaseModule
                )
            )
        }
    }
}