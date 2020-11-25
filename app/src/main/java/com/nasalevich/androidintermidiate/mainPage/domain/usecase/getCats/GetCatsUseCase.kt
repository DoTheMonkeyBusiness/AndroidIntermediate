package com.nasalevich.androidintermidiate.mainPage.domain.usecase.getCats

import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel

interface GetCatsUseCase {

    fun invoke(): List<CatModel>
}
