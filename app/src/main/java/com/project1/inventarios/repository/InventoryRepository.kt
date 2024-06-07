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

    suspend fun insertInventory(inventory:Inventory): Int{
        return withContext(Dispatchers.IO){
            try {
                dao.insertInventories(inventory)
                1
            }catch (e: Exception){
                Log.e("insertInventory catch",e.message.toString())
                0
            }
        }
    }

    suspend fun updateInventory(inventory:Inventory): Int{
        return withContext(Dispatchers.IO){
            try {
                dao.putInventory(inventory)
            }catch (e: Exception){
                Log.e("updateInventory catch",e.message.toString())
                0
            }
        }
    }

    suspend fun getInventory(id:Int): Inventory?{
        return withContext(Dispatchers.IO){
            try {
                dao.getInventory(id)
            }catch (e: Exception){
                Log.e("getInventory catch ",e.message.toString())
                null
            }
        }
    }
}