package com.sample.pokedex.presentation.datasource

import androidx.room.TypeConverter

class IntegerListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<Int> {
        return stringListString.split(",").map { it.toIntOrNull() ?: 0 }
    }

    @TypeConverter
    fun toString(stringList: List<Int>): String {
        return stringList.joinToString(separator = ",")
    }
}