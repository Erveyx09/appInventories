package com.project1.inventarios.repository

import android.util.Log
import com.project1.inventarios.model.CardInventory
import com.project1.inventarios.model.Inventory
import com.project1.inventarios.room.dao.CardInventoryDao
import com.project1.inventarios.room.dao.InventoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CardInventoryRepository(
    private val dao: CardInventoryDao
) {

    suspend fun getAllInventory(): List<CardInventory>?{
        return withContext(Dispatchers.IO){
            try {
                dao.getAllQuantity()
            }catch (e: Exception){
                Log.e("getAllInventory catch",e.message.toString())
                null
            }
        }
    }

    suspend fun getAllInventoryFromText(text:String): List<CardInventory>?{
        return withContext(Dispatchers.IO){
            try {
                dao.getAllQuantityFromNameOrSerial(text)
            }catch (e: Exception){
                Log.e("getAllInventoryFromText catch",e.message.toString())
                null
            }
        }
    }

    suspend fun putInventory(quantity:Int, id:Int): Int{
        return withContext(Dispatchers.IO){
            try {
                dao.updateInventory(quantity, id)
                1
            }catch (e: Exception){
                Log.e("putInventory catch",e.message.toString())
                0
            }
        }
    }

    suspend fun deleteInventory(id:Int): Int{
        return withContext(Dispatchers.IO){
            try {
                dao.deleteInventory(id)
            }catch (e: Exception){
                Log.e("deleteInventory catch",e.message.toString())
                0
            }
        }
    }

}