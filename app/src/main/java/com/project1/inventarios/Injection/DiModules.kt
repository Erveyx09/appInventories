package com.project1.inventarios.Injection

import android.content.Context
import com.project1.inventarios.repository.CardInventoryRepository
import com.project1.inventarios.repository.InventoryRepository
import com.project1.inventarios.room.AppRoomDataBase
import com.project1.inventarios.room.dao.CardInventoryDao
import com.project1.inventarios.room.dao.InventoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * En esta version se retiro applicationComponent por SingletonComponent
 *https://github.com/google/dagger/releases/tag/dagger-2.28.2
 * **/

@Module
@InstallIn(SingletonComponent::class)
object DiModules {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):AppRoomDataBase{
        return AppRoomDataBase.getDataBase(
            context = context
        )
    }

    @Singleton
    @Provides
    fun provideInventoryDao(appRoomDataBase: AppRoomDataBase):InventoryDao{
        return appRoomDataBase.getInventoriesDao()
    }

    @Singleton
    @Provides
    fun provideCardInventoryDao(appRoomDataBase: AppRoomDataBase):CardInventoryDao{
        return appRoomDataBase.getCardInventoriesDao()
    }


    @Singleton
    @Provides
    fun provideInventoryRepository(dao:InventoryDao):InventoryRepository{
        return InventoryRepository(
            dao = dao
        )
    }

    @Singleton
    @Provides
    fun provideCardInventoryRepository(dao:CardInventoryDao):CardInventoryRepository{
        return CardInventoryRepository(
            dao = dao
        )
    }


}