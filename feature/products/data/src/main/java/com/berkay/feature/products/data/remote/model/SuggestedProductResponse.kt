package com.berkay.feature.products.data.remote.model

import com.google.gson.annotations.SerializedName

data class SuggestedProductResponse(
    @SerializedName("products")
    val products: List<SuggestedProductDto>,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class SuggestedProductDto(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("imageURL")
    val imageURL: String? = null,
    @SerializedName("squareThumbnailURL")
    val squareThumbnailURL: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("priceText")
    val priceText: String? = null,
    @SerializedName("shortDescription")
    val shortDescription: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("unitPrice")
    val unitPrice: Double? = null,
    @SerializedName("status")
    val status: Int? = null
)