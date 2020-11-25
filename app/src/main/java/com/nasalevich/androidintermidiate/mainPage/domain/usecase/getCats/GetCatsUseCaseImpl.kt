package com.nasalevich.androidintermidiate.mainPage.domain.usecase.getCats

import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.mainPage.domain.repository.CatsRepository

class GetCatsUseCaseImpl(
    private val repository: CatsRepository
) : GetCatsUseCase {

    override fun invoke(): List<CatModel> = repository.getCats()
}
