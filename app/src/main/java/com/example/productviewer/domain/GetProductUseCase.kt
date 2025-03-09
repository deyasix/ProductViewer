package com.example.productviewer.domain

import com.example.productviewer.domain.entity.Product
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productsDataSource: ProductsDataSource) {
    suspend operator fun invoke(id: Long): Product {
        return productsDataSource.getProduct(id)
    }
}