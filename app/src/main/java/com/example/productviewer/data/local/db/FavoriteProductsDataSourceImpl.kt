package com.example.productviewer.data.local.db

import com.example.productviewer.data.local.db.dao.FavoriteProductsDao
import com.example.productviewer.data.local.db.dto.ProductDto
import com.example.productviewer.domain.FavoriteProductsDataSource
import com.example.productviewer.domain.entity.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteProductsDataSourceImpl(private val favoriteProductsDao: FavoriteProductsDao) :
    FavoriteProductsDataSource {
    override fun getFavoriteProducts(): Flow<List<Product>> {
        return favoriteProductsDao.getFavoriteProducts()
            .map { it.map { productDto -> productDto.toDomainModel() } }
    }

    override fun isFavoriteProduct(productId: Long): Flow<Boolean> {
        return favoriteProductsDao.isFavoriteProduct(productId)
    }

    override suspend fun addFavoriteProduct(product: Product) {
        favoriteProductsDao.addFavoriteProduct(ProductDto(product))
    }

    override suspend fun deleteFavoriteProduct(productId: Long) {
        favoriteProductsDao.deleteFavoriteProduct(productId)
    }
}