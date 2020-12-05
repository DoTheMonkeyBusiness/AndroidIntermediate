package com.nasalevich.androidintermidiate.mainPage.domain.usecase.editCat

import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel

interface EditCatUseCase {

    fun invoke(catModel: CatModel): List<CatModel>
}
