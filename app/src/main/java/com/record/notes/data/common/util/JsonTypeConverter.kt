package com.record.notes.data.common.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.record.notes.data.source.local.NewInfo

class JsonTypeConverter {
    @TypeConverter
    fun fromJson(json: String): List<NewInfo> {
        val listType = object : TypeToken<List<NewInfo>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun toJson(list: List<NewInfo>): String {
        return Gson().toJson(list)
    }
}