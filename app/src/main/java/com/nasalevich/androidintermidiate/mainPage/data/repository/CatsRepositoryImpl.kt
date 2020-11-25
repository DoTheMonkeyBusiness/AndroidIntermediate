package com.nasalevich.androidintermidiate.mainPage.data.repository

import com.nasalevich.androidintermidiate.mainPage.data.mapper.CatsResponseMapper
import com.nasalevich.androidintermidiate.mainPage.data.service.CatsService
import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.mainPage.domain.repository.CatsRepository

class CatsRepositoryImpl(
    private val service: CatsService
) : CatsRepository {

    override fun getCats(): List<CatModel> {
        val catsResponse = service.getResponse()

        return CatsResponseMapper().map(catsResponse)
    }

    override fun editCat(catModel: CatModel): List<CatModel> {
        val catsResponse = service.getResponse()

        return CatsResponseMapper().map(catsResponse)
            .map { if (it.id == catModel.id) catModel else it }
    }
}
