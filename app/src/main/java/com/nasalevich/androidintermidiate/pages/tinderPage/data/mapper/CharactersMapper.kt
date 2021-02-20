package com.nasalevich.androidintermidiate.pages.tinderPage.data.mapper

import com.nasalevich.androidintermidiate.pages.tinderPage.data.entity.CharacterResultsEntity
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel
import com.nasalevich.androidintermidiate.utils.Mapper

class CharactersMapper : Mapper<CharacterResultsEntity, List<CharacterModel>> {

    override fun map(input: CharacterResultsEntity): List<CharacterModel> {
        return input.results.map {
            CharacterModel(
                id = it.id,
                name = it.name,
                description = it.species,
                image = it.image
            )
        }
    }
}
