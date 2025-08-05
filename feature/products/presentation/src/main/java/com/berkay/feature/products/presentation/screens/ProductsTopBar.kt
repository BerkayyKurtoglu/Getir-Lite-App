package com.berkay.feature.products.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.berkay.ui.components.cardtotalbutton.CartTotalButton
import com.berkay.ui.extensions.noRippleClickable
import com.berkay.ui.theme.LocalColorScheme

@Composable
fun ProductsTopBar(
    modifier: Modifier = Modifier,
    cartPrice: String,
    titleText: String,
    showCartTotalButton: Boolean,
    onCartClick: () -> Unit
) {
    val localColorScheme = LocalColorScheme.current
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .drawBehind {
                drawRect(
                    color = localColorScheme.corePrimaryColor
                )
            }
    ) {
        val (title, count) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(title){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            text = titleText,
            color = localColorScheme.mainGetirWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

        AnimatedVisibility(
            modifier = Modifier.constrainAs(count) {
                end.linkTo(parent.end, 16.dp)
                top.linkTo(parent.top, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
            },
            visible = showCartTotalButton,
            enter = fadeIn(animationSpec = tween(durationMillis = 300)),
            exit = fadeOut(animationSpec = tween(durationMillis = 300))
        ) {
            CartTotalButton(
                modifier = Modifier
                    .noRippleClickable { onCartClick() },
                cartPrice = cartPrice
            )
        }
    }
}
