package com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.getCats

import com.nasalevich.androidintermidiate.pages.mainPage.domain.model.CatModel

interface GetCatsUseCase {

    fun invoke(): List<CatModel>
}
