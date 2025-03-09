package com.example.productviewer.domain.entity

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val imageUrl: String
)