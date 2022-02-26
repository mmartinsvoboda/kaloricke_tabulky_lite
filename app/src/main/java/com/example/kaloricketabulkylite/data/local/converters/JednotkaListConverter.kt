package com.example.kaloricketabulkylite.data.local.converters

import androidx.room.TypeConverter
import com.example.kaloricketabulkylite.data.local.entity.potravina.Jednotka
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JednotkaListConverter {

    @TypeConverter
    fun gsonToListOfJednotka(string: String): List<Jednotka> {
        val itemType = object : TypeToken<List<Jednotka>>() {}.type
        return Gson().fromJson(
            string,
            itemType
        )
    }

    @TypeConverter
    fun fromListOfJednotkaToGson(map: List<Jednotka>): String {
        return Gson().toJson(map)
    }

}