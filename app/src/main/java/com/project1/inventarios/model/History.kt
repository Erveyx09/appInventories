package com.project1.inventarios.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Timestamp

@Entity(tableName = "history")
class History(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    var note:String,
    var nameProduct:String,
    var noReference:String,
    var date: Date,
    var audit_date: Timestamp
)