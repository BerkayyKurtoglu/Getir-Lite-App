package com.berkay.ui.components.CardControlButton

import androidx.annotation.DrawableRes
import com.berkay.ui.R

data class CardControlButtonConfig(
    @DrawableRes
    val increaseActionIcon: Int = R.drawable.ic_getir_add,
    @DrawableRes
    val decreaseActionIcon: Int = R.drawable.ic_getir_decrease,
    @DrawableRes
    val decreaseActionDefaultIcon: Int = R.drawable.ic_getir_trash,
)
