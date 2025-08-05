package com.berkay.feature.count.presentation

import com.berkay.feature.common.domain.CartCacheModel
import javax.inject.Inject

class CartMapper @Inject constructor() {

    fun mapToCartProducts(cartCacheModels: List<CartCacheModel>): List<CartProduct> =
        cartCacheModels.map { mapToCartProduct(it) }

    fun mapToCacheModel(product: CartProduct): CartCacheModel =
        CartCacheModel(
            id = product.id,
            name = product.name,
            attribute = product.attribute,
            price = product.price,
            count = product.count,
            image = product.imageUrl
        )

    private fun mapToCartProduct(cartCacheModel: CartCacheModel): CartProduct =
        CartProduct(
            id = cartCacheModel.id,
            name = cartCacheModel.name,
            attribute = cartCacheModel.attribute,
            price = cartCacheModel.price,
            count = cartCacheModel.count,
            imageUrl = cartCacheModel.image
        )

}
