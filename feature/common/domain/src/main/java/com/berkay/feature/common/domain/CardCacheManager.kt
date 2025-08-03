package com.berkay.feature.common.domain

import kotlinx.coroutines.flow.StateFlow

interface CardCacheManager {

    val cartCache: StateFlow<List<CardCacheModel>>

    fun increaseCountByIdOrAdd(item: CardCacheModel)

    fun decreaseCountById(id: String)

}
