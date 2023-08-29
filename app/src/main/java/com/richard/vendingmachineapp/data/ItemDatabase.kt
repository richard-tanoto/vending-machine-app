package com.richard.vendingmachineapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.richard.vendingmachineapp.data.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
}