package com.nasalevich.androidintermidiate.pages.mainPage.domain.repository

import com.nasalevich.androidintermidiate.pages.mainPage.domain.model.CatModel

interface CatsRepository {

    fun getCats(): List<CatModel>

    fun editCat(catModel: CatModel): List<CatModel>
}
