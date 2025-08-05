package com.berkay.feature.products.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.berkay.ui.components.productcard.ProductCard
import com.berkay.ui.components.productcard.ProductUiModel
import com.berkay.ui.theme.LocalColorScheme
import kotlinx.collections.immutable.PersistentList

@Composable
fun ProductsScreen(
    isLoading: Boolean,
    contentInnerPadding: PaddingValues,
    verticalProducts: PersistentList<ProductUiModel>,
    horizontalProducts: PersistentList<ProductUiModel>,
    onProductClick: (ProductUiModel) -> Unit,
    onAddVerticalProductClick: (ProductUiModel) -> Unit,
    onRemoveVerticalProductClick: (ProductUiModel) -> Unit,
    onAddHorizontalProductClick: (ProductUiModel) -> Unit,
    onRemoveHorizontalProductClick: (ProductUiModel) -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentInnerPadding),
    ) {
        if (horizontalProducts.isNotEmpty()){
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .background(LocalColorScheme.current.mainGetirWhite)
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp),
                ) {
                    items(horizontalProducts) { product ->
                        ProductCard(
                            isLoading = isLoading,
                            productUiModel = product,
                            onAddClick = { onAddHorizontalProductClick(product) },
                            onRemoveClick = { onRemoveHorizontalProductClick(product) },
                            onProductClick = {
                                onProductClick(product)
                            }
                        )
                    }
                }
            }
        }

        items(verticalProducts.chunked(3)) { rowProducts ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
            ) {
                rowProducts.forEach { product ->
                    key(product.id) {
                        ProductCard(
                            productUiModel = product,
                            modifier = Modifier.weight(1f),
                            onAddClick = { onAddVerticalProductClick(product) },
                            onRemoveClick = { onRemoveVerticalProductClick(product) },
                            onProductClick = {
                                onProductClick(product)
                            }
                        )
                    }
                }
                repeat(3 - rowProducts.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

    }

}
