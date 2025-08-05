package com.berkay.ui.components.horizontalcartcontrolbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.berkay.ui.components.CardControlButton.CardControlButtonConfig
import com.berkay.ui.theme.MainColorScheme

data class HorizontalCardControlButtonSizeConfig(
    val height: Dp = 54.dp,
    val iconButtonSize: Dp = 54.dp,
    val countBoxSize: Dp = 54.dp,
    val cornerRadius: Dp = 10.dp,
    val shadowElevation: Dp = 2.dp
)

@Composable
fun HorizontalCardControlButton(
    modifier: Modifier = Modifier,
    count: Int,
    config: CardControlButtonConfig = CardControlButtonConfig(),
    sizeConfig: HorizontalCardControlButtonSizeConfig = HorizontalCardControlButtonSizeConfig(),
    onAddClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .height(sizeConfig.height)
            .shadow(
                elevation = sizeConfig.shadowElevation,
                shape = RoundedCornerShape(sizeConfig.cornerRadius),
                clip = false
            )
            .clip(RoundedCornerShape(sizeConfig.cornerRadius))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.size(sizeConfig.iconButtonSize),
            onClick = onRemoveClick
        ) {
            val icon = if (count > 1) config.decreaseActionIcon else config.decreaseActionDefaultIcon
            Icon(
                painter = painterResource(icon),
                contentDescription = "Remove",
                tint = MainColorScheme.corePrimaryColor
            )
        }

        Box(
            modifier = Modifier
                .size(sizeConfig.countBoxSize)
                .background(MainColorScheme.corePrimaryColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = count.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        IconButton(
            modifier = Modifier.size(sizeConfig.iconButtonSize),
            onClick = onAddClick
        ) {
            Icon(
                painter = painterResource(config.increaseActionIcon),
                contentDescription = "Add",
                tint = MainColorScheme.corePrimaryColor
            )
        }
    }
}
