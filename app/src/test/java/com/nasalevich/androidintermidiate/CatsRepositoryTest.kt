package com.nasalevich.androidintermidiate

import com.nasalevich.androidintermidiate.mainPage.data.entity.CatEntity
import com.nasalevich.androidintermidiate.mainPage.data.entity.CatsResponseEntity
import com.nasalevich.androidintermidiate.mainPage.data.repository.CatsRepositoryImpl
import com.nasalevich.androidintermidiate.mainPage.data.service.CatsService
import com.nasalevich.androidintermidiate.mainPage.domain.model.CatModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CatsRepositoryTest {

    @RelaxedMockK
    private lateinit var catsService: CatsService
    @InjectMockKs
    private lateinit var catsRepository: CatsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { catsService.getResponse() } returns catsResponseEntity
    }

    @Test
    fun getCats() {
        val list = listOf(
            CatModel(
                id = 0,
                name = "Markiza",
                description = "Grey and fluffy"
            ),
            CatModel(
                id = 1,
                name = "Vaska",
                description = "White and fluffy"
            )
        )

        assertEquals(catsRepository.getCats(), list)
    }

    @Test
    fun editCat() {
        val model = CatModel(
            id = 0,
            name = "Maus",
            description = "Grey and fluffy"
        )

        assertEquals(catsRepository.editCat(model).first { it.id == model.id }, model)
    }

    companion object {
        private val list = listOf(
            CatEntity(
                id = 0,
                name = "Markiza",
                description = "Grey and fluffy"
            ),
            CatEntity(
                id = 1,
                name = "Vaska",
                description = "White and fluffy"
            )
        )
        private val catsResponseEntity = CatsResponseEntity(cats = list)
    }
}