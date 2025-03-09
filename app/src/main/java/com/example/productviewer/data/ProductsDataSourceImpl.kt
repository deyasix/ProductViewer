package com.example.productviewer.data

import com.example.productviewer.data.remote.ProductsService
import com.example.productviewer.domain.ProductsDataSource
import com.example.productviewer.domain.entity.Product

class ProductsDataSourceImpl(private val productsService: ProductsService) : ProductsDataSource {
    override suspend fun getProducts(): List<Product> {
        return productsService.getProducts().map { it.toDomainModel() }
    }

    override suspend fun getProduct(id: Long): Product {
        return productsService.getProduct(id).toDomainModel()
    }
}