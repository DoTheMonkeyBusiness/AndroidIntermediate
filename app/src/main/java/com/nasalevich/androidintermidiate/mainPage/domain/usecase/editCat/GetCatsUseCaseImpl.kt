package com.nasalevich.androidintermidiate.mainPage.domain.usecase.editCat

import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.mainPage.domain.repository.CatsRepository

class EditCatUseCaseImpl(
    private val repository: CatsRepository
) : EditCatUseCase {

    override fun invoke(catModel: CatModel): List<CatModel> = repository.editCat(catModel)
}
