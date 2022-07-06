package com.example.mvvmNewsApp.core.db

import androidx.room.TypeConverter
import com.example.mvvmNewsApp.feature.data.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String? {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}