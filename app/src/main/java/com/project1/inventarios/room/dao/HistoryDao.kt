package com.project1.inventarios.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project1.inventarios.model.History

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History)

    @Query("select * from history")
    suspend fun getAllHistories():List<History>?

    @Query("select * from history WHERE nameProduct LIKE :text OR noReference LIKE :text")
    suspend fun getAllHistoriesFromNameOrSerial(text:String):List<History>?
}