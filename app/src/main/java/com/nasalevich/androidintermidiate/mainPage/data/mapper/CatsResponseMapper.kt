package com.nasalevich.androidintermidiate.mainPage.data.mapper

import com.nasalevich.androidintermidiate.mainPage.data.entity.CatsResponseEntity
import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.utils.Mapper

class CatsResponseMapper : Mapper<CatsResponseEntity, List<CatModel>> {

    override fun map(input: CatsResponseEntity): List<CatModel> {
        return input.cats.map {
            CatModel(
                id = it.id,
                name = it.name,
                description = it.description
            )
        }
    }
}
