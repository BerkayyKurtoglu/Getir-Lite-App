package com.berkay.feature.detail.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.berkay.ui.R
import com.berkay.ui.components.cardtotalbutton.CartTotalButton
import com.berkay.ui.components.contional.Conditional
import com.berkay.ui.extensions.noRippleClickable
import com.berkay.ui.theme.LocalColorScheme

@Composable
fun ProductDetailTopBar(
    modifier: Modifier = Modifier,
    titleText: String,
    cartPrice: String,
    showCartTotalButton: Boolean,
    onBackClicked: () -> Unit,
    onCartClicked: () -> Unit
) {

    val localColorScheme = LocalColorScheme.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .drawBehind {
                drawRect(
                    color = localColorScheme.corePrimaryColor
                )
            },
        contentAlignment = Alignment.BottomCenter
    ) {
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
            val (back, title, count) = createRefs()
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(back) {
                        start.linkTo(parent.start, 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .noRippleClickable {
                        onBackClicked.invoke()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_getir_back),
                    contentDescription = "BackButton"
                )
            }

            Text(
                modifier = Modifier
                    .constrainAs(title) {
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

            Conditional({showCartTotalButton}) {
                CartTotalButton(
                    modifier = Modifier
                        .constrainAs(count) {
                            end.linkTo(parent.end, 16.dp)
                            top.linkTo(parent.top, 8.dp)
                            bottom.linkTo(parent.bottom, 8.dp)
                        }
                        .noRippleClickable { onCartClicked() },
                    cartPrice = cartPrice
                )
            }
        }
    }

}
