package com.richard.vendingmachineapp.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.richard.vendingmachineapp.data.ItemRepository
import com.richard.vendingmachineapp.data.Result
import com.richard.vendingmachineapp.data.model.Item

class MainViewModel(private val itemRepository: ItemRepository): ViewModel() {

    val itemList: LiveData<Result<List<Item>>> = itemRepository.getItems().asLiveData()

    fun updateItem(item: Item) = itemRepository.updateItem(item)
}