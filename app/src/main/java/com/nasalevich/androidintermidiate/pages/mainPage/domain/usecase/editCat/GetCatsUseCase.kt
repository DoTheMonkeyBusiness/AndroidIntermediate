package com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.editCat

import com.nasalevich.androidintermidiate.pages.mainPage.domain.model.CatModel

interface EditCatUseCase {

    fun invoke(catModel: CatModel): List<CatModel>
}
