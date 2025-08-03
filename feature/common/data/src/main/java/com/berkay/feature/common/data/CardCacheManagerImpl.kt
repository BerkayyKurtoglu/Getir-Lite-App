package com.berkay.feature.common.data

import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.common.domain.CardCacheModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardCacheManagerImpl @Inject constructor() : CardCacheManager {

    private val _cartCache = MutableStateFlow(emptyList<CardCacheModel>())
    override val cartCache = _cartCache.asStateFlow()

    override fun increaseCountByIdOrAdd(item: CardCacheModel) {
        _cartCache.update { currentList ->
            val existingItem = currentList.find { it.id == item.id }

            if (existingItem != null) {
                currentList.map {
                    if (it.id == item.id) {
                        it.copy(count = it.count + 1)
                    } else {
                        it
                    }
                }
            } else {
                currentList + item.copy(count = 1)
            }
        }
    }

    override fun decreaseCountById(id: String) {
        _cartCache.update { currentList ->
            currentList.mapNotNull { item ->
                when {
                    item.id == id && item.count > 1 -> item.copy(count = item.count - 1)
                    item.id == id && item.count <= 1 -> null
                    else -> item
                }
            }
        }
    }
}
