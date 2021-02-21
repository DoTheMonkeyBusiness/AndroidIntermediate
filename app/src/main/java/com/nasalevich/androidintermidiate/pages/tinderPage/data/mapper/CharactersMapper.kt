package com.nasalevich.androidintermidiate.pages.tinderPage.data.mapper

import com.nasalevich.androidintermidiate.pages.tinderPage.data.entity.CharacterEntity
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel
import com.nasalevich.androidintermidiate.utils.Mapper

class CharactersMapper : Mapper<List<CharacterEntity>, List<CharacterModel>> {

    override fun map(input: List<CharacterEntity>): List<CharacterModel> {
        return input.filter { it.id != null }.map {
            CharacterModel(
                id = it.id!!,
                name = it.name ?: "",
                description = it.species ?: "",
                image = it.image ?: ""
            )
        }
    }
}
