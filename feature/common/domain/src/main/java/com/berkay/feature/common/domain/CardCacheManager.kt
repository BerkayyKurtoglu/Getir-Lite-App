package com.berkay.feature.common.domain

import kotlinx.coroutines.flow.StateFlow

interface CardCacheManager {

    val cartCache: StateFlow<List<CartCacheModel>>

    fun increaseCountByIdOrAdd(item: CartCacheModel)

    fun decreaseCountById(id: String)

    fun emptyCart()
}
