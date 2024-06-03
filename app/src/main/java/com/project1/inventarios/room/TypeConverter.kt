package com.project1.inventarios.room

import androidx.room.TypeConverter
import java.sql.Date
import java.sql.Timestamp

class TypeConverter {

    @TypeConverter
    fun dateToString(date: Date?):String?{
        if (date!=null){
            return date.toString()
        }
        return null
    }

    @TypeConverter
    fun stringToDate(date: String?):Date?{
        if (date!=null){
            return Date.valueOf(date)
        }
        return Date.valueOf(date)
    }

    @TypeConverter
    fun timeStampToString(timestamp: Timestamp?):String?{
        if (timestamp!=null){
            return timestamp.toString()
        }
        return null
    }


    @TypeConverter
    fun stringToTimestamp(timestamp: String?):Timestamp?{
        if (timestamp!=null){
            return Timestamp.valueOf(timestamp)
        }
        return null
    }

}