package com.project1.inventarios.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.project1.inventarios.model.CardInventory

@Dao
interface CardInventoryDao {

    @Query("select id,noReference,name,representation,representationName,equivalences,equivalencesName,quantity,deliveryBy from inventory")
    suspend fun getAllQuantity():List<CardInventory>?

    @Query("select id,noReference,name,representation,representationName,equivalences,equivalencesName,quantity,deliveryBy from inventory WHERE name LIKE :text OR noReference LIKE :text")
    suspend fun getAllQuantityFromNameOrSerial(text:String):List<CardInventory>?

    @Query("UPDATE inventory SET representation = :representation, quantity = :quantity WHERE id =:id")
    suspend fun updateInventory(id:Int,representation:Int, quantity:Int): Int

    @Query("DELETE FROM inventory WHERE id = :id")
    suspend fun deleteInventory(id:Int): Int
}