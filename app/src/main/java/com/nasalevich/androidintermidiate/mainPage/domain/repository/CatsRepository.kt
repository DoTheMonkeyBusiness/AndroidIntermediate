package com.nasalevich.androidintermidiate.mainPage.domain.repository

import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel

interface CatsRepository {

    fun getCats(): List<CatModel>

    fun editCat(catModel: CatModel): List<CatModel>
}
