package com.berkay.feature.detail.contract

import com.berkay.common.NavigationCommand
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailRoute(
    val id: String,
    val name: String,
    val price: Double,
    val cartPrice: Double,
    val attribute: String,
    val imageUrl: String,
    val count: Int
) : NavigationCommand.Destination
