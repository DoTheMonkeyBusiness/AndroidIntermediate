package com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.getCats

import com.nasalevich.androidintermidiate.pages.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.pages.mainPage.domain.repository.CatsRepository

class GetCatsUseCaseImpl(
    private val repository: CatsRepository
) : GetCatsUseCase {

    override fun invoke(): List<CatModel> = repository.getCats()
}
