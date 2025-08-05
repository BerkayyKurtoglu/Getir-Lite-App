package com.berkay.ui.components.productcard

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.berkay.ui.components.CardControlButton.CardControlButton
import com.berkay.ui.theme.GetirColors

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    productUiModel: ProductUiModel,
    onProductClick: () -> Unit,
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(108.dp)
    ) {
        Box(
            modifier = Modifier
        ) {
            AsyncImage(
                model = productUiModel.imageUrl,
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 8.dp, end = 8.dp)
                    .size(100.dp)
                    .border(
                        width = 1.dp,
                        color = GetirColors.corePrimaryContainerColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        onProductClick()
                    }
            )
            CardControlButton(
                count = productUiModel.count,
                onAddClick = onAddClick,
                onRemoveClick = onRemoveClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = productUiModel.priceText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = GetirColors.corePrimaryColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = productUiModel.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = GetirColors.textDarkColor,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(2.dp))

        if (productUiModel.shouldShowAttribute){
            Text(
                text = productUiModel.attribute,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = GetirColors.textSecondaryColor,
            )
        }
    }
}
