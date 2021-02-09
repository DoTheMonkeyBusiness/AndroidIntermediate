package com.nasalevich.androidintermidiate.pages.mainPage.data.service

import com.nasalevich.androidintermidiate.pages.mainPage.data.entity.CatsResponseEntity
import com.nasalevich.androidintermidiate.mockRepo.MockCatsList

class CatsService {

    fun getResponse(): CatsResponseEntity {
        return CatsResponseEntity(cats = MockCatsList.list)
    }
}
