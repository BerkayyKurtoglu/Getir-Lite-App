package com.berkay.feature.common.data

import app.cash.turbine.test
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.common.domain.CartCacheModel
import com.berkay.test.CoreTestUtil
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CartCacheManagerTest : CoreTestUtil(true) {

    @MockK
    private lateinit var manager: CardCacheManager

    @BeforeEach
    fun setUp() {
        manager = CardCacheManagerImpl()
    }

    @Test
    fun `emptyCart should clear all items from cache`() {
        runTest {
            // given
            val initialItem = CartCacheModel(
                id = "1",
                name = "Test",
                price = 10.0,
                count = 2,
                image = ""
            )
            manager.increaseCountByIdOrAdd(item = initialItem)

            // when
            manager.emptyCart()

            // then
            manager.cartCache.test {
                val result = awaitItem()
                assert(result.isEmpty())
            }

        }
    }

    @Test
    fun `given item that cache contains, when increaseCountById, then the item's count should increase by one`() {
        runTest {
            // given
            val initialItem = CartCacheModel(
                id = "1",
                name = "Test Product",
                price = 20.0,
                count = 1,
                image = "image-url"
            )
            manager.increaseCountByIdOrAdd(initialItem)

            // when
            manager.increaseCountByIdOrAdd(initialItem)

            // then
            manager.cartCache.test {
                val result = awaitItem()
                assertEquals(1, result.size)
                val item = result.first()
                assertEquals(2, item.count)
                assertEquals(initialItem.id, item.id)
                assertEquals(initialItem.name, item.name)
                assertEquals(initialItem.price, item.price, 0.001)
                assertEquals(initialItem.image, item.image)
            }
        }
    }

    @Test
    fun `given item not in cache, when increaseCountByIdOrAdd called, then item should be added with count 1`() {
        runTest {
            // given
            val newItem = CartCacheModel(
                id = "2",
                name = "New Product",
                price = 15.0,
                count = 0,
                image = "image-url"
            )

            // when
            manager.increaseCountByIdOrAdd(newItem)

            // then
            manager.cartCache.test {
                val result = awaitItem()
                assertEquals(1, result.size)
                val addedItem = result.first()
                assertEquals(newItem.id, addedItem.id)
                assertEquals(newItem.name, addedItem.name)
                assertEquals(newItem.price, addedItem.price, 0.001)
                assertEquals(newItem.image, addedItem.image)
                assertEquals(1, addedItem.count)
            }
        }
    }

    @Test
    fun `given item with count greater than 1, when decreaseCountById is called, then count should decrease by one`() =
        runTest {
            // given
            val initialItem = CartCacheModel(
                id = "1",
                name = "Test Product",
                price = 10.0,
                count = 3,
                image = ""
            )
            manager.increaseCountByIdOrAdd(initialItem)
            manager.increaseCountByIdOrAdd(initialItem)

            // when
            manager.decreaseCountById("1")

            // then
            manager.cartCache.test {
                val updatedList = awaitItem()
                assertEquals(1, updatedList.size)
                assertEquals(1, updatedList.first().count)
            }
        }

    @Test
    fun `given item with count equal to 1, when decreaseCountById is called, then item should be removed from cache`() = runTest {
        // given
        val initialItem = CartCacheModel(
            id = "1",
            name = "Test Product",
            price = 10.0,
            count = 1,
            image = ""
        )
        manager.increaseCountByIdOrAdd(initialItem)

        // when
        manager.decreaseCountById("1")

        // then
        manager.cartCache.test {
            val updatedList = awaitItem()
            assert(updatedList.isEmpty())
        }
    }
}
