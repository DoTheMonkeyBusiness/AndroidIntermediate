package com.nasalevich.androidintermidiate.pages.tinderPage.data.service

import com.nasalevich.androidintermidiate.pages.tinderPage.data.entity.CharacterResultsEntity
import retrofit2.http.GET

interface CharactersService {

    @GET("character")
    suspend fun characters(): CharacterResultsEntity?
}