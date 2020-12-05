package com.nasalevich.androidintermidiate.mainPage.data.service

import com.nasalevich.androidintermidiate.mainPage.data.entity.CatsResponseEntity
import com.nasalevich.androidintermidiate.mockRepo.MockCatsList

class CatsService {

    fun getResponse(): CatsResponseEntity {
        return CatsResponseEntity(cats = MockCatsList.list)
    }
}
