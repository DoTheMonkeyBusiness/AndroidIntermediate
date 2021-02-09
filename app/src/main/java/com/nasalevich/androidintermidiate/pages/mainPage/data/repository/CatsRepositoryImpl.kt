package com.nasalevich.androidintermidiate.pages.mainPage.data.repository

import com.nasalevich.androidintermidiate.pages.mainPage.data.mapper.CatsResponseMapper
import com.nasalevich.androidintermidiate.pages.mainPage.data.service.CatsService
import com.nasalevich.androidintermidiate.pages.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.pages.mainPage.domain.repository.CatsRepository

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
