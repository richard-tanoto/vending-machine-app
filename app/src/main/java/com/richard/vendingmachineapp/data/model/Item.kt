package com.richard.vendingmachineapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val name: String,

    val price: Long,

    var stock: Int,

    val imageUrl: String,
)
