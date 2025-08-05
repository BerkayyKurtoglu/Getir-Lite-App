package com.berkay.feature.count.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import com.berkay.feature.count.presentation.CartProduct
import com.berkay.ui.theme.LocalColorScheme
import kotlinx.collections.immutable.PersistentList

@Composable
fun CartScreen(
    innerPaddings: PaddingValues,
    products: PersistentList<CartProduct>,
    onAddClick: (CartProduct) -> Unit,
    onRemoveClick: (CartProduct) -> Unit,
    onProductClick: (CartProduct) -> Unit
) {
    val localColorScheme = LocalColorScheme.current

    LazyColumn(
        modifier = Modifier
            .padding(innerPaddings)
            .drawBehind {
                drawRect(
                    color = localColorScheme.mainGetirWhite
                )
            },
    ) {
        itemsIndexed(
            items = products,
            key = { _, item -> item.id }
        ) { index, item ->
            CartProductRow(
                modifier = Modifier.animateItem(
                    fadeInSpec = null,
                    fadeOutSpec = null
                ),
                product = item,
                onAddClick = { onAddClick(item) },
                onRemoveClick = { onRemoveClick(item) },
                onProductClick = { onProductClick(item) }
            )

            if (index < products.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.animateItem(
                        fadeInSpec = null,
                        fadeOutSpec = null
                    ).padding(horizontal = 16.dp),
                    thickness = 1.5.dp,
                    color = localColorScheme.corePrimaryContainerColor
                )
            }
        }
    }
}