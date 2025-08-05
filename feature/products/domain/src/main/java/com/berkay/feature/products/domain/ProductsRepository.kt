package com.berkay.feature.products.domain

import com.berkay.domain.ResultState
import com.berkay.feature.products.domain.model.Product
import com.berkay.feature.products.domain.model.SuggestedProduct

interface ProductsRepository {

    suspend fun getProducts() : ResultState<List<Product>>

    suspend fun getSuggestedProducts() : ResultState<List<SuggestedProduct>>

}
