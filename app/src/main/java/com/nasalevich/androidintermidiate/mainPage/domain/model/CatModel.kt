package com.nasalevich.androidintermidiate.mainPage.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatModel(
    val id: Int,
    val name: String,
    val description: String
): Parcelable
