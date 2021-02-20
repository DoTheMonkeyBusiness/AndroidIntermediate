package com.nasalevich.androidintermidiate.pages.tinderPage.data.entity

import com.google.gson.annotations.SerializedName

class CharacterResultsEntity(

    @SerializedName("results")
    val results: List<CharacterEntity>
)

class CharacterEntity(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("image")
    val image: String,
)