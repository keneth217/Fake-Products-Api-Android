package com.keneth.products.data.Posts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reactions(
    val dislikes: Int,
    val likes: Int
):Parcelable