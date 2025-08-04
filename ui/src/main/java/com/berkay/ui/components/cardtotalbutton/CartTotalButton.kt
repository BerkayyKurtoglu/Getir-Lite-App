package com.berkay.ui.components.cardtotalbutton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.berkay.ui.R
import com.berkay.ui.theme.GetirColors
import kotlinx.coroutines.delay

@Composable
fun CartTotalButton(
    cartPrice: String,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(true) }
    var previousPrice by remember { mutableStateOf(cartPrice) }

    LaunchedEffect(cartPrice) {
        if (previousPrice != cartPrice) {
            visible = false
            delay(400)
            previousPrice = cartPrice
            visible = true
        }
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .height(40.dp)
            .wrapContentWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_getir_count),
            contentDescription = "Cart",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxHeight()
        )

        AnimatedVisibility(
            visible = visible,
            enter = expandHorizontally(
                expandFrom = Alignment.End,
                animationSpec = tween(400)
            ) + fadeIn(animationSpec = tween(400)),
            exit = shrinkHorizontally(
                shrinkTowards = Alignment.Start,
                animationSpec = tween(400)
            ) + fadeOut(animationSpec = tween(400))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(GetirColors.corePrimaryContainerColor)
                    .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                    .padding(horizontal = 10.dp, vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = cartPrice,
                    color = GetirColors.corePrimaryColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}
