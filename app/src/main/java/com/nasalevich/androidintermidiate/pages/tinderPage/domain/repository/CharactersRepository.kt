package com.nasalevich.androidintermidiate.pages.tinderPage.domain.repository

import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel

interface CharactersRepository {

    suspend fun getCharacters(): List<CharacterModel>
}