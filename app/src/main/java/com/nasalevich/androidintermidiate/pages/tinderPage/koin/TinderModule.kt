package com.nasalevich.androidintermidiate.pages.tinderPage.koin

import com.nasalevich.androidintermidiate.pages.tinderPage.data.repository.CharactersRepositoryImpl
import com.nasalevich.androidintermidiate.pages.tinderPage.data.service.CharactersService
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.repository.CharactersRepository
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.usecase.GetCharactersUseCase
import com.nasalevich.androidintermidiate.pages.tinderPage.presentation.TinderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val tinderModule = module {

    factory { GetCharactersUseCase(repository = get()) }

    factory<CharactersRepository> { CharactersRepositoryImpl(service = get()) }

    single<CharactersService> {
        get<Retrofit>().create(CharactersService::class.java)
    }

    viewModel {
        TinderViewModel(
            getCharactersUseCase = get(),
        )
    }
}