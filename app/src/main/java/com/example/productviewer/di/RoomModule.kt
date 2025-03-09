package com.example.productviewer.di

import android.content.Context
import androidx.room.Room
import com.example.productviewer.data.local.db.AppDatabase
import com.example.productviewer.data.local.db.DatabaseContract
import com.example.productviewer.data.local.db.dao.FavoriteProductsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DatabaseContract.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideTransactionsDao(db: AppDatabase): FavoriteProductsDao {
        return db.getFavoriteProductsDao()
    }

}