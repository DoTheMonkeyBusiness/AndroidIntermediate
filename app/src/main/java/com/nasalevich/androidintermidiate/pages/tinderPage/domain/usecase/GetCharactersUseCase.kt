package com.nasalevich.androidintermidiate.pages.tinderPage.domain.usecase

import com.nasalevich.androidintermidiate.base.usecase.UseCase
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers

class GetCharactersUseCase(
    private val repository: CharactersRepository
) : UseCase<Unit, List<CharacterModel>>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit): List<CharacterModel> {
        return repository.getCharacters()
    }
}