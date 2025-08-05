package com.berkay.feature.products.data

import com.berkay.data.BaseRepository
import com.berkay.domain.ResultState
import com.berkay.domain.mapOnSuccess
import com.berkay.feature.products.data.remote.ProductService
import com.berkay.feature.products.domain.ProductsRepository
import com.berkay.feature.products.domain.model.Product
import com.berkay.feature.products.domain.model.SuggestedProduct
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : BaseRepository(), ProductsRepository {

    override suspend fun getProducts(): ResultState<List<Product>> {
        return callNetwork {
            productService.getProducts()
        }.mapOnSuccess { dtoList ->
            dtoList.firstOrNull()?.let { productResponse ->
                productResponse.products?.map {
                    Product(
                        id = it.id ?: "",
                        name = it.name ?: "",
                        price = it.price ?: 0.0,
                        imageUrl =  when {
                            !it.thumbnailURL.isNullOrBlank() -> it.thumbnailURL
                            !it.imageURL.isNullOrBlank() -> it.imageURL
                            else -> ""
                        },
                        priceText = it.priceText ?: "",
                        attribute = it.attribute ?: "",
                        shortDescription = it.shortDescription ?: ""
                    )
                }
            } ?: listOf()
        }
    }

    override suspend fun getSuggestedProducts(): ResultState<List<SuggestedProduct>> {
        return callNetwork {
            productService.getSuggestedProducts()
        }.mapOnSuccess { dtoList ->
            dtoList.firstOrNull()?.let { suggestedProductResponse ->
                suggestedProductResponse.products.map {
                    SuggestedProduct(
                        id = it.id ?: "",
                        imageURL =  when {
                            !it.squareThumbnailURL.isNullOrBlank() -> it.squareThumbnailURL
                            !it.imageURL.isNullOrBlank() -> it.imageURL
                            else -> ""
                        },
                        price = it.price ?: 0.0,
                        name = it.name ?: "",
                        priceText = it.priceText ?: "",
                    )
                }
            } ?: listOf()
        }
    }
}
