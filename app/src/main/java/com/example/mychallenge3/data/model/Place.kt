package com.example.mychallenge3.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val name: String,
    val description: String,
    val image: String
): Parcelable
