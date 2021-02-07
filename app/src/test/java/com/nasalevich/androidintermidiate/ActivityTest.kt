package com.nasalevich.androidintermidiate

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ActivityTest {

    @ExperimentalCoroutinesApi
    @Test
    fun testKoinStarted() {
        buildActivity(MainActivity::class.java).setup()

        val exception = Assert.assertThrows(KoinAppAlreadyStartedException::class.java) { startKoin { } }

        val expectedMessage = "A Koin Application has already been started"
        val actualMessage = exception.message

        Assert.assertTrue(actualMessage?.contains(expectedMessage) == true)
    }
}
