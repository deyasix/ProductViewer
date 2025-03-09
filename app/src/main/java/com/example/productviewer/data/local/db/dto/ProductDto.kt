package com.example.productviewer.data.local.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.productviewer.data.local.db.DatabaseContract
import com.example.productviewer.domain.entity.Product

@Entity(tableName = DatabaseContract.FavoriteProducts.TABLE_FAVORITE_PRODUCTS)
data class ProductDto(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val imageUrl: String
) {

    constructor(product: Product) : this(
        product.id,
        product.title,
        product.price,
        product.description,
        product.category,
        product.imageUrl
    )

    fun toDomainModel(): Product {
        return Product(id, title, price, description, category, imageUrl)
    }
}