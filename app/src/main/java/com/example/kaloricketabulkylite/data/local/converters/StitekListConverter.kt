package com.example.kaloricketabulkylite.data.local.converters

import androidx.room.TypeConverter
import com.example.kaloricketabulkylite.data.local.entity.potravina.Stitek
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StitekListConverter {

    @TypeConverter
    fun gsonToListOfStitek(string: String): List<Stitek>? {
        val itemType = object : TypeToken<List<Stitek>?>() {}.type
        return Gson().fromJson(
            string,
            itemType
        )
    }

    @TypeConverter
    fun fromListOfStitekToGson(map: List<Stitek>?): String {
        return Gson().toJson(map)
    }

}