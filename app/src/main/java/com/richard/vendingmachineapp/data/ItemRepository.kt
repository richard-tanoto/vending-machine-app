package com.richard.vendingmachineapp.data

import com.richard.vendingmachineapp.data.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Executors

class ItemRepository(private val itemDao: ItemDao) {

    fun getItems(): Flow<Result<List<Item>>> {
        return flow {
            emit(Result.Loading)
            itemDao.getItems().collect { itemList ->
                emit(Result.Success(itemList))
            }
        }.catch {
            emit(Result.Error(it))
        }
    }

    fun updateItem(item: Item) {
        Executors.newSingleThreadExecutor().execute { itemDao.updateItem(item) }
    }

}