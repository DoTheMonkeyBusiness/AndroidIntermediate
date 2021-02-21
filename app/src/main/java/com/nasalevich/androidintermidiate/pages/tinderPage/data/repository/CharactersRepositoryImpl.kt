package com.nasalevich.androidintermidiate.pages.tinderPage.data.repository

import com.nasalevich.androidintermidiate.pages.tinderPage.data.mapper.CharactersMapper
import com.nasalevich.androidintermidiate.pages.tinderPage.data.service.CharactersService
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val service: CharactersService
) : CharactersRepository {

    override suspend fun getCharacters(): List<CharacterModel> {
        val charactersResponse = service.characters()?.results
            ?: throw IllegalArgumentException("Empty response")

        return CharactersMapper().map(charactersResponse)
    }
}