package com.android.finalapp.repository

import com.android.finalapp.data.ItemDao
import com.android.finalapp.model.Item

class ItemRepository(
    private val itemDao: ItemDao
) {
    fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }

    suspend fun getItemById(id: Int): Item? = itemDao.getItemById(id)

}