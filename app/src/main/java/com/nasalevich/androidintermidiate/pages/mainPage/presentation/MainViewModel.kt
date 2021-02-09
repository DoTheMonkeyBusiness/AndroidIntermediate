package com.nasalevich.androidintermidiate.pages.mainPage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasalevich.androidintermidiate.pages.mainPage.domain.model.CatModel
import com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.editCat.EditCatUseCase
import com.nasalevich.androidintermidiate.pages.mainPage.domain.usecase.getCats.GetCatsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val editCatUseCase: EditCatUseCase,
    private val getCatsUseCase: GetCatsUseCase
) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    val state: StateFlow<MainState>
        get() = _state

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    private val catList = mutableListOf<CatModel>()

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchCats -> fetchCats()
                    is MainIntent.EditCat -> editCat(it.catModel)
                    is MainIntent.PressBack -> pressBack()
                    is MainIntent.SelectCat -> selectCat(it.catModel)
                }
            }
        }
    }

    private suspend fun pressBack() {
        if (catList.isNotEmpty()) {
            _state.value = MainState.Success(catList)
        } else {
            fetchCats()
        }
    }

    private fun selectCat(catModel: CatModel) {
        _state.value = MainState.CatSelected(catModel)
    }

    private fun editCat(catModel: CatModel) {
        catList.clear()
        catList.addAll(editCatUseCase.invoke(catModel))

        _state.value = MainState.Success(catList)
    }

    private suspend fun fetchCats() {
        _state.value = MainState.Loading

        delay(2000)

        catList.clear()
        catList.addAll(getCatsUseCase.invoke())

        _state.value = MainState.Success(catList)
    }
}
