package com.nasalevich.androidintermidiate.pages.tinderPage.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasalevich.androidintermidiate.base.usecase.ResultOf
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class TinderViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    val userIntent = Channel<TinderIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<TinderState>(TinderState.Idle)
    val state: StateFlow<TinderState> = _state

    private val characters = ArrayDeque<CharacterModel>()

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is TinderIntent.FetchData -> fetchData()
                    is TinderIntent.Like -> processLike()
                    is TinderIntent.Dislike -> processDislike()
                }
            }
        }
    }

    private fun processDislike() {
        val dislikedCharacter = characters.removeFirstOrNull()
        Log.i(
            "TinderViewModel",
            "there is must be logic that process dislike for $dislikedCharacter, but unfortunately there is just log"
        )

        sendSuccess()
    }

    private fun processLike() {
        val likedCharacter = characters.removeFirstOrNull()
        Log.i(
            "TinderViewModel",
            "there is must be logic that process like for $likedCharacter, but unfortunately there is just log"
        )

        sendSuccess()
    }

    private suspend fun fetchData() {
        _state.value = TinderState.Loading

        when (val resultOf = getCharactersUseCase(Unit)) {
            is ResultOf.Success -> {
                processSuccess(resultOf.value)
            }
            is ResultOf.Failure -> {
                processFailure(resultOf.throwable)
            }
        }
    }

    private fun processFailure(throwable: Throwable) {
        Log.e("TinderViewModel", throwable.message.toString())

        _state.value = TinderState.Error
    }

    private fun processSuccess(resultData: List<CharacterModel>) {
        characters.addAll(resultData)

        sendSuccess()
    }

    private fun sendSuccess() {
        val displayedCharacters = listOfNotNull(
            characters.getOrNull(0),
            characters.getOrNull(1),
        )

        _state.value = TinderState.Success(displayedCharacters)
    }
}