package com.example.productviewer.data.remote

import com.example.productviewer.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {

    @GET("/products")
    suspend fun getProducts(): List<ProductDto>

    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") id: Long): ProductDto
}