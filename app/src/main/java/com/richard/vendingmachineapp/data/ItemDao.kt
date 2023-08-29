package com.richard.vendingmachineapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.richard.vendingmachineapp.data.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun getItems(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: Item)

    @Insert
    fun insertItems(vararg items: Item)

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

}