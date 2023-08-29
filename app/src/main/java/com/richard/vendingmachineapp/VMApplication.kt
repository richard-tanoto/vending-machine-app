package com.richard.vendingmachineapp

import android.app.Application
import com.richard.vendingmachineapp.di.databaseModule
import com.richard.vendingmachineapp.di.repositoryModule
import com.richard.vendingmachineapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class VMApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@VMApplication)
            modules(
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}