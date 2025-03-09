package com.example.productviewer.domain

import com.example.productviewer.domain.entity.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productsDataSource: ProductsDataSource) {
    suspend operator fun invoke(): List<Product> {
        return productsDataSource.getProducts()
    }
}