package com.android.finalapp.model

import androidx.room.TypeConverter

//class RatingConverter {
//    @TypeConverter
//    fun fromString(value: String): List<Rating> {
//        val listType = object : TypeToken<List<Rating>>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun toString(list: List<Rating>): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//}