package com.project1.inventarios.repository

import android.util.Log
import com.project1.inventarios.model.CardInventory
import com.project1.inventarios.model.History
import com.project1.inventarios.room.dao.CardInventoryDao
import com.project1.inventarios.room.dao.HistoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HistoryRepository(
    private val dao: HistoryDao
) {


    suspend fun createHistory(history: History): Int{
        return withContext(Dispatchers.IO){
            try {
                dao.insertHistory(history)
                1
            }catch (e: Exception){
                Log.e("getAllInventory catch",e.message.toString())
                0
            }
        }
    }

    suspend fun getAllHistory(): List<History>?{
        return withContext(Dispatchers.IO){
            try {
                dao.getAllHistories()
            }catch (e: Exception){
                Log.e("getAllInventory catch",e.message.toString())
                null
            }
        }
    }

    suspend fun getAllHistoryFromText(text:String): List<History>?{
        return withContext(Dispatchers.IO){
            try {
                dao.getAllHistoriesFromNameOrSerial(text)
            }catch (e: Exception){
                Log.e("getAllInventoryFromText catch",e.message.toString())
                null
            }
        }
    }


}