package com.berkay.feature.products.presentation

import com.berkay.feature.common.domain.CartCacheModel
import com.berkay.feature.products.domain.model.Product
import com.berkay.feature.products.domain.model.SuggestedProduct
import com.berkay.ui.components.productcard.ProductUiModel
import javax.inject.Inject

private const val DEFAULT_COUNT = 0

class ProductsMapper @Inject constructor() {

    fun mapProductsToProductsUiModel(products: List<Product>): List<ProductUiModel> =
        products.map { mapToProductUiModel(it) }

    fun mapSuggestedProductsToProductsUiModel(suggestedProducts: List<SuggestedProduct>): List<ProductUiModel> =
        suggestedProducts.map { mapToProductUiModel(it) }

    private fun mapToProductUiModel(product: Product): ProductUiModel =
        ProductUiModel(
            id = product.id,
            name = product.name,
            attribute = product.attribute,
            price = product.price,
            priceText = product.priceText,
            imageUrl = product.imageUrl,
            count = DEFAULT_COUNT
        )

    private fun mapToProductUiModel(product: SuggestedProduct): ProductUiModel =
        ProductUiModel(
            id = product.id,
            name = product.name,
            price = product.price,
            attribute = "",
            priceText = product.priceText,
            imageUrl = product.imageURL,
            count = DEFAULT_COUNT
        )

    fun mapToCartCacheModel(product: ProductUiModel): CartCacheModel =
        CartCacheModel(
            id = product.id,
            image = product.imageUrl,
            name = product.name,
            price = product.price,
            count = 1,
            attribute = product.attribute
        )

}