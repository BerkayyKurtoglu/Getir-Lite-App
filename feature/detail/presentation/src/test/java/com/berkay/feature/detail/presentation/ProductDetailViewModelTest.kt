package com.berkay.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.berkay.common.Navigator
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.common.domain.CartCacheModel
import com.berkay.feature.detail.contract.ProductDetailRoute
import com.berkay.test.CoreTestUtil
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ProductDetailViewModelTest : CoreTestUtil(true) {

    @MockK
    private lateinit var cacheManager: CardCacheManager

    @MockK
    private lateinit var navigator: Navigator

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: ProductDetailViewModel

    private val savedStateProductDetail = ProductDetailRoute(
        id = "123",
        name = "Ürün",
        price = 45.0,
        imageUrl = "url",
        attribute = "att",
        count = 3,
        cartPrice = 45.0
    )

    @BeforeEach
    fun before() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { savedStateHandle.toRoute<ProductDetailRoute>() } returns savedStateProductDetail
        every { cacheManager.cartCache } returns MutableStateFlow(emptyList())
    }

    @AfterEach
    fun after() {
        clearAllMocks()
    }

    @Test
    fun `when ViewModel is initialized, then initial state should be set correctly`() {
        // given
        val expectedState = ProductDetailUiState(
            title = "Ürün Detayı",
            id = "123",
            name = "Ürün",
            price = 45.0,
            cartPrice = 0.0,
            attribute = "att",
            imageUrl = "url",
            count = 0
        )

        // when
        initViewModel()
        val actualState = viewModel.uiState.value

        // then
        Assertions.assertEquals(expectedState, actualState)
    }

    @Test
    fun `when cart cache emits, then uiState should be updated with correct count and cartPrice`() {
        runTest {
            // given
            val productId = "123"
            val productName = "Test Product"
            val productPrice = 50.0
            val imageUrl = "image-url"

            val cachedProducts = listOf(
                CartCacheModel(
                    id = productId,
                    name = productName,
                    price = productPrice,
                    count = 2,
                    image = imageUrl
                ),
                CartCacheModel(id = "999", name = "Other", price = 30.0, count = 1, image = "")
            )

            every { cacheManager.cartCache } returns MutableStateFlow(cachedProducts)

            // when
            initViewModel()

            // then
            viewModel.uiState.test {
                val state = awaitItem()
                Assertions.assertEquals(2, state.count)
                Assertions.assertEquals(130.0, state.cartPrice, 0.001)
                cancel()
            }
        }
    }

    @Test
    fun `when AddProduct event is handled, then cacheManager's increaseCountByIdOrAdd is invoked with correct CartCacheModel`() {
        runTest {
            // given
            val productId = "1"
            val productName = "Test Product"
            val productPrice = 19.99
            val attribute = "Test Attr"
            val imageUrl = "https://test.com/image.jpg"
            val initialCount = 3

            // Stub the route arguments
            every { savedStateHandle.toRoute<ProductDetailRoute>() } returns ProductDetailRoute(
                id = productId,
                name = productName,
                price = productPrice,
                cartPrice = 0.0,
                attribute = attribute,
                imageUrl = imageUrl,
                count = initialCount
            )

            val initialCart = listOf(CartCacheModel(
                id = productId,
                name = productName,
                price = productPrice,
                image = imageUrl,
                count = initialCount
            ))
            every { cacheManager.cartCache } returns MutableStateFlow(initialCart)

            // when
            initViewModel()
            viewModel.handleEvent(ProductDetailAction.AddProduct)

            // then
            val expectedCacheModel = CartCacheModel(
                id = productId,
                name = productName,
                price = productPrice,
                image = imageUrl,
                count = initialCount
            )
            verify(exactly = 1) { cacheManager.increaseCountByIdOrAdd(expectedCacheModel) }
        }
    }

    @Test
    fun `when RemoveProduct event is handled, then cacheManager's decreaseCountById is invoked with correct id`() {
        runTest {
            // given
            val productId = "1"
            val productName = "Test Product"
            val productPrice = 19.99
            val attribute = "Test Attr"
            val imageUrl = "https://test.com/image.jpg"
            val productCount = 1

            // Stub the route arguments
            every { savedStateHandle.toRoute<ProductDetailRoute>() } returns ProductDetailRoute(
                id = productId,
                name = productName,
                price = productPrice,
                cartPrice = 0.0,
                attribute = attribute,
                imageUrl = imageUrl,
                count = productCount
            )

            // Seed cache so listenCart doesn't override count
            val initialCartProducts = listOf(
                CartCacheModel(
                    id = productId,
                    name = productName,
                    price = productPrice,
                    image = imageUrl,
                    count = productCount
                )
            )
            every { cacheManager.cartCache } returns MutableStateFlow(initialCartProducts)

            // when
            initViewModel()
            viewModel.handleEvent(ProductDetailAction.RemoveProduct)

            // then
            verify(exactly = 1) { cacheManager.decreaseCountById(productId) }
        }
    }

    @Test
    fun `when OnBackClick event is handled, then navigator navigateUp should be called`() = runTest {
        // given
        coEvery { navigator.navigateUp() } returns Unit

        // when
        initViewModel()
        viewModel.handleEvent(ProductDetailAction.OnBackClick)

        // then
        coVerify(exactly = 1) { navigator.navigateUp() }
    }

    @Test
    fun `when OnCartClick event is handled, then navigator navigate to cart should be called`() = runTest {
        // given
        coEvery { navigator.navigate(any()) } returns Unit

        // when
        initViewModel()
        viewModel.handleEvent(ProductDetailAction.OnCartClick)

        // then
        coVerify(exactly = 1) { navigator.navigate(any()) }
    }

    private fun initViewModel() {
        viewModel = ProductDetailViewModel(
            cacheManager = cacheManager,
            navigator = navigator,
            savedStateHandle = savedStateHandle
        )
    }
}
