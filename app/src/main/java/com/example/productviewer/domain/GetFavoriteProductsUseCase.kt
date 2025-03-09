package com.example.productviewer.domain

import com.example.productviewer.domain.entity.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteProductsUseCase @Inject constructor(private val favoriteProductsRepository: FavoriteProductsRepository) {

    operator fun invoke(): Flow<List<Product>> {
        return favoriteProductsRepository.getFavoriteProducts()
    }
}