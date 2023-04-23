package com.android.finalapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.finalapp.model.Item
import com.android.finalapp.model.Rating

@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: Item)
    @Insert
    suspend fun insertRating(rating: Rating)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<Item>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItemById(id: Int): Item?

    @Query("SELECT * FROM ratings WHERE itemId = :itemId")
    fun getItemRatings(itemId: Int): List<Rating>
}