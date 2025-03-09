package com.example.productviewer.domain

import com.example.productviewer.domain.entity.Product

interface ProductsDataSource {
    suspend fun getProducts(): List<Product>
    suspend fun getProduct(id: Long): Product
}