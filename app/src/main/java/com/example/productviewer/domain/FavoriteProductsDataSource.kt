package com.example.productviewer.domain

import com.example.productviewer.domain.entity.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteProductsDataSource {
    fun getFavoriteProducts(): Flow<List<Product>>
    fun isFavoriteProduct(productId: Long): Flow<Boolean>
    suspend fun addFavoriteProduct(product: Product)
    suspend fun deleteFavoriteProduct(productId: Long)
}