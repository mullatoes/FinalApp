package com.android.finalapp.data

import androidx.room.Dao
import androidx.room.Insert
import com.android.finalapp.model.Rating

@Dao
interface RatingDao {

    @Insert
    suspend fun insertRating(rating: Rating)
}