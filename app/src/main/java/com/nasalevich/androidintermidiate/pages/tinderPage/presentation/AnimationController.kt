package com.nasalevich.androidintermidiate.pages.tinderPage.presentation

import androidx.compose.animation.AnimatedFloatModel
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.AnimationClockObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AnimationController(
    clock: AnimationClockObservable,
    private val screenWidthPx: Float,
) {

    private val animationClockProxy: AnimationClockObservable = object : AnimationClockObservable {
        override fun subscribe(observer: AnimationClockObserver) {
            isAnimationRunning = true
            clock.subscribe(observer)
        }

        override fun unsubscribe(observer: AnimationClockObserver) {
            isAnimationRunning = false
            clock.unsubscribe(observer)
        }
    }

    val offsetX = AnimatedFloatModel(0f, animationClockProxy)

    var isAnimationRunning: Boolean by mutableStateOf(false)
        private set

    var onSwipeLeft: () -> Unit = {}
    var onSwipeRight: () -> Unit = {}

    fun swipeLeft() {
        offsetX.animateTo(-screenWidthPx) { _, _ ->
            onSwipeLeft()
            offsetX.snapTo(0f)
        }
    }

    fun swipeRight() {
        offsetX.animateTo(screenWidthPx) { _, _ ->
            onSwipeRight()
            offsetX.snapTo(0f)
        }
    }

    fun returnCenter() {
        offsetX.animateTo(0f) { _, _ ->
            offsetX.snapTo(0f)
        }
    }
}