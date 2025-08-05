package com.berkay.feature.products.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("productCount")
    val productCount: Int? = null,
    @SerializedName("products")
    val products: List<ProductDto>? = null
)

data class ProductDto(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("attribute")
    val attribute: String? = null,
    @SerializedName("shortDescription")
    val shortDescription: String? = null,
    @SerializedName("thumbnailURL")
    val thumbnailURL: String? = null,
    @SerializedName("imageURL")
    val imageURL: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("priceText")
    val priceText: String? = null
)
