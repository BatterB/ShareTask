package com.example.data.utilities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    @TypeConverter
    fun converterMapToJson(map: Map<String,Boolean>) : String?{
        return Gson().toJson(map)
    }

    @TypeConverter
    fun converterJsonToMap(string: String) :Map<String,Boolean>{
        return Gson().fromJson(
            string,
            object : TypeToken<Map<String,Boolean>>() {}.type
        )
    }

    @TypeConverter
    fun converterListToJson(list: List<String>) : String?{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun converterJsonToList(string: String) : List<String>{
        return Gson().fromJson(
            string,
            object : TypeToken<List<String>>() {}.type
        )
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}