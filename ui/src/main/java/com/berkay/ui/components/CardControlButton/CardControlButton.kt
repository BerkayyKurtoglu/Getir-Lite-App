package com.berkay.ui.components.CardControlButton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.berkay.ui.theme.GetirColors
import com.berkay.ui.theme.MainColorScheme

private const val MIN_COUNT = 0

@Composable
fun CardControlButton(
    modifier: Modifier = Modifier,
    count: Int,
    config: CardControlButtonConfig = CardControlButtonConfig(),
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    val showFullView: Boolean = count > MIN_COUNT
    Column(
        modifier = modifier
            .width(32.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                clip = false
            )
            .background(Color.White, RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            modifier = Modifier
                .size(32.dp),
            onClick = onAddClick
        ) {
            Icon(
                painter = painterResource(config.increaseActionIcon),
                contentDescription = "Add",
                tint = MainColorScheme.corePrimaryColor
            )
        }

        AnimatedVisibility(visible = showFullView) {
            val decreaseActionIcon = if (count > 1) {
                config.decreaseActionIcon
            } else {
                config.decreaseActionDefaultIcon
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(GetirColors.corePrimaryColor),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 4.dp),
                        text = count.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }

                IconButton(
                    modifier = Modifier
                        .size(32.dp),
                    onClick = onRemoveClick,
                ) {
                    Icon(
                        painter = painterResource(decreaseActionIcon),
                        contentDescription = "Add",
                        tint = MainColorScheme.corePrimaryColor
                    )
                }
            }
        }
    }
}
