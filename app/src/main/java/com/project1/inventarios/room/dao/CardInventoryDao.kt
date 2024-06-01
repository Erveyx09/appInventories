package com.project1.inventarios.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.project1.inventarios.model.CardInventory

@Dao
interface CardInventoryDao {

    @Query("select id,name,quantity,serialNumber from inventory")
    suspend fun getAllQuantity():List<CardInventory>?

    @Query("select id,name,quantity,serialNumber from inventory WHERE name LIKE :text OR serialNumber LIKE :text")
    suspend fun getAllQuantityFromNameOrSerial(text:String):List<CardInventory>?

    @Query("UPDATE inventory SET quantity = :quantity WHERE id =:id")
    suspend fun updateInventory(quantity:Int, id:Int): Int
}