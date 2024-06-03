package com.project1.inventarios.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Date
import java.sql.Timestamp

@Entity(tableName = "inventory")
data class Inventory (
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    val setting:String,
    val noReference:String,
    var name:String,
    val representation:Int,
    val representationName:String,
    val equivalences:Int,
    val equivalencesName:String,
    val quantity:Int,

    val expires:Date?,
    val portion:String,
    val equipment:String,
    val deliveryBy:String,
    var auditDate:Timestamp
):Serializable