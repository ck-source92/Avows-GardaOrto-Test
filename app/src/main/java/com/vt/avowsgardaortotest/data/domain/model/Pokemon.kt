package com.vt.avowsgardaortotest.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val url: String,
    val name: String
) : Parcelable