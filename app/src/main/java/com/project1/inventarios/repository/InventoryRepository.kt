package com.project1.inventarios.repository

import android.util.Log
import com.project1.inventarios.model.Inventory
import com.project1.inventarios.room.dao.InventoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class InventoryRepository(
    private val dao: InventoryDao
) {

    suspend fun getAllInventory(): List<Inventory>?{
        return withContext(Dispatchers.IO){
            try {
                dao.getAll()
            }catch (e: Exception){
                Log.e("catch",e.message.toString())
                null
            }
        }
    }

    suspend fun insertInventory(inventory:Inventory): Int{
        return withContext(Dispatchers.IO){
            try {
                dao.insertInventories(inventory)
                1
            }catch (e: Exception){
                Log.e("catch",e.message.toString())
                0
            }
        }
    }
}