package com.project1.inventarios.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project1.inventarios.model.Inventory

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllInventories(list: List<Inventory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventories(inventory:Inventory)

    @Query("delete from inventory where id = :id")
    suspend fun deleteInventoryById(id:Int)

    @Query("select * from inventory")
    suspend fun getAll():List<Inventory>

}