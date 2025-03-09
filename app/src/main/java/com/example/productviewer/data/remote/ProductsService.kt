package com.example.productviewer.data.remote

import com.example.productviewer.data.remote.dto.ProductDto
import retrofit2.http.GET

interface ProductsService {

    @GET("/products")
    suspend fun getProducts(): List<ProductDto>
}