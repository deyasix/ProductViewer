package com.example.productviewer.domain

import com.example.productviewer.domain.entity.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteProductsRepository @Inject constructor(private val favoriteProductsDataSource: FavoriteProductsDataSource) {
    fun getFavoriteProducts(): Flow<List<Product>> {
        return favoriteProductsDataSource.getFavoriteProducts()
    }

    fun isFavoriteProduct(productId: Long): Flow<Boolean> {
        return favoriteProductsDataSource.isFavoriteProduct(productId)
    }

    suspend fun addFavoriteProduct(product: Product) {
        favoriteProductsDataSource.addFavoriteProduct(product)
    }

    suspend fun deleteFavoriteProduct(productId: Long) {
        favoriteProductsDataSource.deleteFavoriteProduct(productId)
    }
}