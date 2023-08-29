package com.richard.vendingmachineapp.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.richard.vendingmachineapp.data.ItemDatabase
import com.richard.vendingmachineapp.data.ItemRepository
import com.richard.vendingmachineapp.data.preferences.PreferenceHelper
import com.richard.vendingmachineapp.feature.main.MainViewModel
import com.richard.vendingmachineapp.util.DataUtil
import kotlinx.coroutines.coroutineScope
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.concurrent.Executors

val databaseModule = module {
    factory { get<ItemDatabase>().itemDao() }
    single {
        Room.databaseBuilder(
            androidContext(), ItemDatabase::class.java, "Item.db"
        ).addCallback(object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Executors.newSingleThreadExecutor().execute { DataUtil.fillWithStartingData(androidContext(), get<ItemDatabase>().itemDao()) }
            }
        }).fallbackToDestructiveMigration().build()
    }
}

val viewModelModule = module {
    single { MainViewModel(get()) }
}

val repositoryModule = module {
    single { ItemRepository(get()) }
}