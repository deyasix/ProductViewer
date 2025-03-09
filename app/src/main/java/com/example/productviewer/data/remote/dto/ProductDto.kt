package com.example.productviewer.data.remote.dto

import com.example.productviewer.domain.entity.Product
import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val imageUrl: String
) {
    fun toDomainModel(): Product {
        return Product(id, title, price, description, category, imageUrl)
    }
}