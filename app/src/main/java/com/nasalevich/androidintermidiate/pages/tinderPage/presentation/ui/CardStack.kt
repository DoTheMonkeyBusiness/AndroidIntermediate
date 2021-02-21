package com.nasalevich.androidintermidiate.pages.tinderPage.presentation.ui

import androidx.compose.animation.asDisposableClock
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.rawDragGestureFilter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.AmbientAnimationClock
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.dp
import com.nasalevich.androidintermidiate.pages.tinderPage.domain.model.CharacterModel
import com.nasalevich.androidintermidiate.pages.tinderPage.presentation.AnimationController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.math.absoluteValue

@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@Composable
fun CardStack(
    items: List<CharacterModel>,
    modifier: Modifier = Modifier,
    onSwipeLeft: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
) {
    val screenWidthPx = with(AmbientDensity.current) {
        AmbientConfiguration.current.screenWidthDp.dp.toPx()
    }
    val clock = AmbientAnimationClock.current.asDisposableClock()
    val animationController = remember {
        AnimationController(
            clock = clock,
            screenWidthPx = screenWidthPx,
        )
    }
    animationController.onSwipeRight = onSwipeLeft
    animationController.onSwipeLeft = onSwipeRight

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .rawDragGestureFilter(
                dragObserver = TinderDragObserver(
                    animationController = animationController,
                    offsetToConfirm = screenWidthPx * 0.3
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        StackContent(
            items = items,
            animationController = animationController,
        )
    }
}

@ExperimentalComposeUiApi
@Composable
private fun StackContent(
    items: List<CharacterModel>,
    animationController: AnimationController,
) {
    if (items.size < 2) {
        CardMessageView(
            text = "No more items",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.8f),
        )
    }

    items.asReversed().forEachIndexed { index, characterModel ->
        TinderCard(
            characterModel = characterModel,
            modifier = Modifier
                .moveTo((if (index == items.lastIndex) animationController.offsetX.value else 0).toInt())
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.8f),
        )
    }
}

private fun Modifier.moveTo(
    x: Int,
) = this.then(Modifier.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x, 0)
    }
})

private const val VELOCITY_TO_CONFIRM = 5000.0

private class TinderDragObserver(
    private val animationController: AnimationController,
    private val offsetToConfirm: Double,
) : DragObserver {
    override fun onDrag(dragDistance: Offset): Offset {
        if (animationController.isAnimationRunning) return super.onDrag(dragDistance)

        val offset = animationController.offsetX
        offset.snapTo(offset.value + dragDistance.x)

        return super.onDrag(dragDistance)
    }

    override fun onStop(velocity: Offset) {
        super.onStop(velocity)

        val offset = animationController.offsetX.value

        when {
            isCanSwipeUsingVelocity(velocity.x, offset)
                    || offset.absoluteValue > offsetToConfirm -> doSwipe(offset)
            else -> animationController.returnCenter()
        }
    }

    private fun isCanSwipeUsingVelocity(velocity: Float, offset: Float): Boolean {
        return velocity.absoluteValue > VELOCITY_TO_CONFIRM && (velocity > 0) == (offset > 0)
    }

    private fun doSwipe(offset: Float) {
        when {
            offset > 0 -> animationController.swipeRight()
            else -> animationController.swipeLeft()
        }
    }
}
