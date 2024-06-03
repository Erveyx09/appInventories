package com.project1.inventarios.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project1.inventarios.model.Inventory
import com.project1.inventarios.room.dao.CardInventoryDao
import com.project1.inventarios.room.dao.InventoryDao
import com.project1.inventarios.utils.Constants.NAME_DATABASE

@Database(entities = arrayOf(
   Inventory::class
),version = 5)
@TypeConverters(TypeConverter::class)
abstract class AppRoomDataBase: RoomDatabase() {
    companion object{
        private var appRoomDataBase:AppRoomDataBase? = null

        fun getDataBase(context: Context):AppRoomDataBase{
            if(appRoomDataBase==null){
                appRoomDataBase =
                    Room.databaseBuilder(context,AppRoomDataBase::class.java,NAME_DATABASE)
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return appRoomDataBase!!
        }
    }

    abstract fun getInventoriesDao():InventoryDao
    abstract fun getCardInventoriesDao():CardInventoryDao

}