package com.berkay.feature.count.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.berkay.feature.count.presentation.CartProduct
import com.berkay.ui.components.contional.Conditional
import com.berkay.ui.components.horizontalcartcontrolbutton.HorizontalCardControlButton
import com.berkay.ui.components.horizontalcartcontrolbutton.HorizontalCardControlButtonSizeConfig
import com.berkay.ui.extensions.noRippleClickable
import com.berkay.ui.theme.LocalColorScheme

@Composable
fun CartProductRow(
    modifier: Modifier,
    product: CartProduct,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
    onProductClick: () -> Unit
) {
    val localColorScheme = LocalColorScheme.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .noRippleClickable {
                onProductClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 1.dp,
                        color = localColorScheme.corePrimaryContainerColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(Color.White)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Conditional({ product.attribute.isNotEmpty() }) {
                    Text(
                        text = product.attribute,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Gray
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = product.priceText,
                    color = localColorScheme.corePrimaryColor,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(
            modifier = Modifier.width(12.dp)
        )
        HorizontalCardControlButton(
            count = product.count,
            onAddClick = onAddClick,
            onRemoveClick = onRemoveClick,
            sizeConfig = HorizontalCardControlButtonSizeConfig(
                height = 40.dp,
                iconButtonSize = 40.dp,
                countBoxSize = 40.dp,
                cornerRadius = 8.dp,
                shadowElevation = 1.dp
            )
        )
    }
}

