package com.example.productviewer.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productviewer.data.local.db.dao.FavoriteProductsDao
import com.example.productviewer.data.local.db.dto.ProductDto

@Database(
    entities = [ProductDto::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFavoriteProductsDao(): FavoriteProductsDao
}