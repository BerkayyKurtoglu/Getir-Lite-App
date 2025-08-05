package com.berkay.feature.products.domain.model

data class Product(
    val id: String,
    val name: String,
    val attribute: String,
    val imageUrl: String,
    val price: Double,
    val priceText: String,
    val shortDescription: String
)

