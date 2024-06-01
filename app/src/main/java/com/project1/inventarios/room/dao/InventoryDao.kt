package com.project1.inventarios.room.dao

import androidx.room.*
import com.project1.inventarios.model.Inventory

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllInventories(list: List<Inventory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventories(inventory:Inventory)

    @Query("delete from inventory where id = :id")
    suspend fun deleteInventoryById(id:Int)

    @Query("select * from inventory where id = :id")
    suspend fun getInventory(id:Int):Inventory

    @Update
    suspend fun putInventory(inventory:Inventory):Int

}