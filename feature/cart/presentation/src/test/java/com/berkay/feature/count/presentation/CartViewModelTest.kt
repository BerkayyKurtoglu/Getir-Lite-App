package com.berkay.feature.count.presentation

import app.cash.turbine.test
import com.berkay.common.Navigator
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.common.domain.CartCacheModel
import com.berkay.test.CoreTestUtil
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CartViewModelTest : CoreTestUtil(useUnconfinedDispatcher = true) {

    @MockK
    private lateinit var cacheManager: CardCacheManager

    @MockK
    private lateinit var mapper: CartMapper

    @MockK
    private lateinit var navigator: Navigator

    private lateinit var viewModel: CartViewModel

    private val mockCacheCartProducts = listOf(
        CartCacheModel(id = "1", name = "Ürün 1", price = 15.00, count = 2, image = "image-url"),
        CartCacheModel(id = "2", name = "Ürün 2", price = 30.00, count = 2, image = "image-url")
    )

    private val mockMappedCartProducts = listOf(
        CartProduct(id = "1", name = "Ürün 1", imageUrl = "image-url", price = 15.00, count = 2),
        CartProduct(id = "2", name = "Ürün 2", imageUrl = "image-url", price = 30.00, count = 2)
    )

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { cacheManager.cartCache } returns MutableStateFlow(mockCacheCartProducts)
        every { mapper.mapToCartProducts(mockCacheCartProducts) } returns mockMappedCartProducts
        initViewModel()
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `when cart cache is empty, then navigateUp should be called`() = runTest {
        // given
        every { cacheManager.cartCache } returns MutableStateFlow(emptyList())

        // initialize ViewModel
        initViewModel()

        // then
        coVerify(exactly = 1) { navigator.navigateUp() }
    }

    @Test
    fun `when cart has items, then uiState should be updated with mapped cart products`() {
        runTest {
            // given
            val expectedUiState = CartUiState(
                bottomButtonText = "Siparişi Tamamla",
                confirmCheckoutDialogState = ConfirmCheckoutDialogState(
                    title = "Siparişiniz alınacak onaylıyor musunuz ?",
                    confirmText = "Evet",
                    dismissText = "Hayır"
                ),
                cartProducts = mockMappedCartProducts.toPersistentList()
            )

            // when

            // then
            viewModel.uiState.test {
                val uiState = awaitItem()
                Assertions.assertEquals(uiState.cartProducts,expectedUiState.cartProducts)
                Assertions.assertEquals(uiState.bottomButtonText,expectedUiState.bottomButtonText)
                Assertions.assertEquals(uiState.confirmCheckoutDialogState,expectedUiState.confirmCheckoutDialogState)
            }

        }
    }

    @Test
    fun `when AddProduct action, then increaseCountByIdOrAdd should be called`(){
        runTest {
            // given
            val product = CartProduct(id = "1", name = "Ürün 1", imageUrl = "image-url", price = 15.00, count = 2, attribute = "attribute")
            val expectedCacheModel = CartCacheModel(id = "1", name = "Ürün 1", price = 15.00, count = 2, image = "image-url", attribute = "attribute")

            every { mapper.mapToCacheModel(product) } returns expectedCacheModel

            // when
            viewModel.handleAction(CartAction.AddProduct(product))

            // then
            verify(exactly = 1) {
                cacheManager.increaseCountByIdOrAdd(expectedCacheModel)
            }
        }
    }

    @Test
    fun `when RemoveProduct action, then decreaseCountById should be called`(){
        runTest {
            // given
            val product = CartProduct(id = "1", name = "Ürün 1", imageUrl = "image-url", price = 15.00, count = 2, attribute = "attribute")

            // when
            viewModel.handleAction(CartAction.RemoveProduct(product))

            // then
            verify(exactly = 1) {
                cacheManager.decreaseCountById(product.id)
            }
        }
    }

    @Test
    fun `when RemoveAllProduct action, then increaseCountByIdOrAdd should be called`(){
        runTest {
            // given

            // when
            viewModel.handleAction(CartAction.OnRemoveAllClicked)

            // then
            verify(exactly = 1) {
                cacheManager.emptyCart()
            }
        }
    }

    @Test
    fun `when CheckoutButtonClicked action, then uiState should be updated to show dialog`(){
        runTest {
            // given

            // when
            viewModel.handleAction(CartAction.OnCheckoutButtonClicked)

            // then
            viewModel.uiState.test {
                val uiState = awaitItem()
                Assertions.assertEquals(uiState.showCheckoutDialog, true)
            }
        }
    }

    @Test
    fun `when OnDismissCheckoutDialog action, then uiState should be updated to hide dialog`(){
        runTest {
            // given

            // when
            viewModel.handleAction(CartAction.OnDismissCheckoutDialog)

            // then
            viewModel.uiState.test {
                val uiState = awaitItem()
                Assertions.assertEquals(uiState.showCheckoutDialog, false)
            }
        }
    }

    @Test
    fun `when OnConfirmCheckoutDialog action, then uiState should be updated to hide dialog and empty cart`(){
        runTest {
            // given

            // when
            viewModel.handleAction(CartAction.OnConfirmCheckoutDialog)
            advanceUntilIdle()

            // then
            viewModel.uiState.test {
                val uiState = awaitItem()
                Assertions.assertEquals(uiState.showCheckoutDialog, false)
            }

            verify {
                cacheManager.emptyCart()
            }
        }
    }

    @Test
    fun `when OnBackClicked action, then navigateUp should be called`() {
        runTest {
            // given

            // when
            viewModel.handleAction(CartAction.OnBackClicked)

            // then
            coVerify(exactly = 1) { navigator.navigateUp() }
        }
    }

    private fun initViewModel() {
        viewModel = CartViewModel(
            cacheManager = cacheManager,
            mapper = mapper,
            navigator = navigator
        )
    }
}
