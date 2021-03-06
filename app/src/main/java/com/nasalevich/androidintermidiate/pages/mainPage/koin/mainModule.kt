package com.nasalevich.androidintermidiate.pages.mainPage.koin

import com.nasalevich.androidintermidiate.pages.mainPage.data.repository.CatsRepositoryImpl
import com.nasalevich.androidintermidiate.pages.mainPage.data.service.CatsService
import com.nasalevich.androidintermidiate.pages.mainPage.domain.repository.CatsRepository
import com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.editCat.EditCatUseCase
import com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.editCat.EditCatUseCaseImpl
import com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.getCats.GetCatsUseCase
import com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.getCats.GetCatsUseCaseImpl
import com.nasalevich.androidintermidiate.pages.mainPage.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { CatsService() }

    factory<CatsRepository> { CatsRepositoryImpl(service = get()) }
    factory<EditCatUseCase> { EditCatUseCaseImpl(repository = get()) }
    factory<GetCatsUseCase> { GetCatsUseCaseImpl(repository = get()) }

    viewModel {
        MainViewModel(
            editCatUseCase = get(),
            getCatsUseCase = get()
        )
    }
}
