package com.keneth.products.data.Quotes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quote(
    val author: String,
    val id: Int,
    val quote: String
): Parcelable