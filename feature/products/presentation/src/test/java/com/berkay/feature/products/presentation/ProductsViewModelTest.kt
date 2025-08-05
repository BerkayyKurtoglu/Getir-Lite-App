package com.berkay.feature.products.presentation

import app.cash.turbine.test
import com.berkay.common.Navigator
import com.berkay.domain.ResultState
import com.berkay.feature.common.domain.CardCacheManager
import com.berkay.feature.common.domain.CartCacheModel
import com.berkay.feature.count.common.CartScreenRoute
import com.berkay.feature.detail.contract.ProductDetailRoute
import com.berkay.feature.products.domain.ProductsRepository
import com.berkay.test.CoreTestUtil
import com.berkay.ui.components.productcard.ProductUiModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ProductsViewModelTest : CoreTestUtil(true){

    @MockK
    private lateinit var cacheManager: CardCacheManager

    @MockK
    private lateinit var repository: ProductsRepository

    @MockK
    private lateinit var mapper: ProductsMapper

    @MockK
    private lateinit var navigator: Navigator

    private lateinit var viewModel: ProductsViewModel

    @BeforeEach
    fun beforeEachTest(){
        MockKAnnotations.init(this, relaxUnitFun = true)

        coEvery { repository.getProducts() } returns ResultState.Success(listOf())
        coEvery { repository.getSuggestedProducts() } returns ResultState.Success(listOf())
        every { cacheManager.cartCache } returns MutableStateFlow(listOf())
        every { mapper.mapProductsToProductsUiModel(any()) } returns listOf()
        every { mapper.mapSuggestedProductsToProductsUiModel(any()) } returns listOf()

        initViewModel()
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `when onCartClick action, then should navigate to cart screen`(){
        runTest {
            // given

            // when
            viewModel.handleAction(ProductsAction.OnCartClick)

            // then
            coVerify {
                navigator.navigate(CartScreenRoute)
            }
        }
    }

    @Test
    fun `when OnAddVerticalProduct action, then increaseCountByIdOrAdd should be called`(){
        runTest {
            // given
            val productUiModel = ProductUiModel(
                id = "1",
                name = "Ürün 1",
                price = 10.00,
                imageUrl = "",
                count = 1,
                attribute = "",
                priceText = "10.00"
            )
            val mappedCacheModel = CartCacheModel(
                id = "1",
                name = "Ürün 1",
                price = 10.00,
                count = 1,
                image = "",
                attribute = ""
            )
            every { mapper.mapToCartCacheModel(productUiModel) } returns mappedCacheModel

            // when
            viewModel.handleAction(ProductsAction.OnAddVerticalProduct(productUiModel))

            // then
            verify {
                cacheManager.increaseCountByIdOrAdd(mappedCacheModel)
            }
        }
    }

    @Test
    fun `when addHorizontalProductToCount action, then increaseCountByIdOrAdd should be called`(){
        runTest {
            // given
            val productUiModel = ProductUiModel(
                id = "1",
                name = "Ürün 1",
                price = 10.00,
                imageUrl = "",
                count = 1,
                attribute = "",
                priceText = "10.00"
            )
            val mappedCacheModel = CartCacheModel(
                id = "1",
                name = "Ürün 1",
                price = 10.00,
                count = 1,
                image = "",
                attribute = ""
            )
            every { mapper.mapToCartCacheModel(productUiModel) } returns mappedCacheModel

            // when
            viewModel.handleAction(ProductsAction.OnAddHorizontalProduct(productUiModel))

            // then
            verify {
                cacheManager.increaseCountByIdOrAdd(mappedCacheModel)
            }
        }
    }

    @Test
    fun `when OnRemoveVerticalProduct action, then decreaseCountById should be called`(){
        runTest {
            // given
            val productUiModel = ProductUiModel(
                id = "1",
                name = "Ürün 1",
                price = 10.00,
                imageUrl = "",
                count = 1,
                attribute = "",
                priceText = "10.00"
            )

            // when
            viewModel.handleAction(ProductsAction.OnRemoveVerticalProduct(productUiModel))

            // then
            verify {
                cacheManager.decreaseCountById(productUiModel.id)
            }
        }
    }

    @Test
    fun `when OnRemoveHorizontalProduct action, then decreaseCountById should be called`(){
        runTest {
            // given
            val productUiModel = ProductUiModel(
                id = "1",
                name = "Ürün 1",
                price = 10.00,
                imageUrl = "",
                count = 1,
                attribute = "",
                priceText = "10.00"
            )

            // when
            viewModel.handleAction(ProductsAction.OnRemoveHorizontalProduct(productUiModel))

            // then
            verify {
                cacheManager.decreaseCountById(productUiModel.id)
            }
        }
    }

    @Test
    fun `when OnProductClicked action, then should navigate to product detail screen`(){
        runTest {
            // given
            val productUiModel = ProductUiModel(
                id = "1",
                name = "Ürün 1",
                price = 10.00,
                imageUrl = "",
                count = 1,
                attribute = "",
                priceText = "10.00"
            )

            // when
            viewModel.handleAction(ProductsAction.OnProductClick(productUiModel))

            // then
            coVerify {
                navigator.navigate(
                    ProductDetailRoute(
                        id = productUiModel.id,
                        name = productUiModel.name,
                        price = productUiModel.price,
                        cartPrice = viewModel.uiState.value.screenState.cartPrice,
                        attribute = productUiModel.attribute,
                        imageUrl = productUiModel.imageUrl,
                        count = productUiModel.count
                    )
                )
            }
        }
    }

    @Test
    fun `when OnConfirmErrorDialog action, then should update ui error state to null`(){
        runTest {
            // given

            // when
            viewModel.handleAction(ProductsAction.OnConfirmErrorDialog)

            // then
            viewModel.uiState.test {
                val state = awaitItem()
                Assertions.assertEquals(state.errorState, null)
            }

        }
    }

    private fun initViewModel(){
        viewModel = ProductsViewModel(
            cacheManager = cacheManager,
            repository = repository,
            mapper = mapper,
            navigator = navigator
        )
    }
}
