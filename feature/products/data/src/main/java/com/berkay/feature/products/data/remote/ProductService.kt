package com.berkay.feature.products.data.remote

import com.berkay.feature.products.data.remote.model.ProductResponse
import com.berkay.feature.products.data.remote.model.SuggestedProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("api/products")
    suspend fun getProducts(): Response<List<ProductResponse>>

    @GET("api/suggestedProducts")
    suspend fun getSuggestedProducts(): Response<List<SuggestedProductResponse>>
}
