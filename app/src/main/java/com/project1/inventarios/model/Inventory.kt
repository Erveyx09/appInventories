package com.project1.inventarios.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Timestamp

@Entity(tableName = "inventory")
data class Inventory (
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    var name:String,
    var quantity:Int,
    var description:String,
    var gender:String,
    var serialNumber:String,
    var auditDate:String
):Serializable