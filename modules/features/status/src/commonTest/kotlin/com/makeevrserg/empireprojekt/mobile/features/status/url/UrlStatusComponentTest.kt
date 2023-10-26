package com.makeevrserg.empireprojekt.mobile.features.status.url

import app.cash.turbine.test
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.url.data.UrlStatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UrlStatusComponentTest {
    private fun createUrlStatusComponent(block: () -> Boolean): DefaultUrlStatusComponent {
        return DefaultUrlStatusComponent(
            title = "TITLE",
            storeFactory = DefaultStoreFactory(),
            urlStatusRepository = object : UrlStatusRepository {
                override suspend fun isActive(): Result<Boolean> {
                    delay(200L)
                    return runCatching { block.invoke() }
                }
            }
        )
    }

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(Dispatchers.Default)
    }

    @Test
    fun testInitialLoadingState(): Unit = runBlocking {
        val urlStatusComponent = createUrlStatusComponent { true }
        urlStatusComponent.model.test {
            assertEquals(UrlStatusComponent.LoadingStatus.LOADING, awaitItem().status)
        }
    }

    @Test
    fun testSuccessState(): Unit = runBlocking {
        val urlStatusComponent = createUrlStatusComponent { true }
        urlStatusComponent.model.test {
            skipItems(1)
            assertEquals(UrlStatusComponent.LoadingStatus.SUCCESS, awaitItem().status)
        }
    }

    @Test
    fun testFailureState(): Unit = runBlocking {
        val urlStatusComponent = createUrlStatusComponent { false }
        urlStatusComponent.model.test {
            skipItems(1)
            assertEquals(UrlStatusComponent.LoadingStatus.ERROR, awaitItem().status)
        }
    }

    @Test
    fun testFailureWithExceptionState(): Unit = runBlocking {
        val urlStatusComponent = createUrlStatusComponent { error("Hello world") }
        urlStatusComponent.model.test {
            skipItems(1)
            assertEquals(UrlStatusComponent.LoadingStatus.ERROR, awaitItem().status)
        }
    }
}
