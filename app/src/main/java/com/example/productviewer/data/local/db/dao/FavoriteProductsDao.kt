package com.example.productviewer.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productviewer.data.local.db.DatabaseContract
import com.example.productviewer.data.local.db.dto.ProductDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductsDao {

    @Query("SELECT * FROM ${DatabaseContract.FavoriteProducts.TABLE_FAVORITE_PRODUCTS}")
    fun getFavoriteProducts(): Flow<List<ProductDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteProduct(productDto: ProductDto)

    @Query("DELETE FROM ${DatabaseContract.FavoriteProducts.TABLE_FAVORITE_PRODUCTS} WHERE id = :productId")
    suspend fun deleteFavoriteProduct(productId: Long)

    @Query("SELECT EXISTS(SELECT * FROM ${DatabaseContract.FavoriteProducts.TABLE_FAVORITE_PRODUCTS} WHERE id = :productId)")
    fun isFavoriteProduct(productId: Long): Flow<Boolean>

}