package com.example.myassigapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myassigapplication.presentation.currencyExchange.viewModel.CurrencyExchangeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrencyExchangeViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CurrencyExchangeViewModel

    @Mock
    private lateinit var fakeCurrencyRepository: FakeCurrencyRepository

    @Before
    fun setUp(){
        `when`(fakeCurrencyRepository.getCurrencySymbols()).thenReturn(JsonData.getCurrencySymbolsWithFlow())
        `when`(fakeCurrencyRepository.getCurrencyExchangeRates()).thenReturn(JsonData.getExchangeRatesWithFlow())

        viewModel = CurrencyExchangeViewModel(fakeCurrencyRepository)
    }

    @Test
    fun testCurrencyList() {
        Assert.assertEquals(6, viewModel.baseCurrencyList.value?.size)
    }

    @Test
    fun testCurrencyNames() {
        Assert.assertEquals("AUD", viewModel.baseCurrencyList.value?.get(0))
        Assert.assertEquals("CAD", viewModel.baseCurrencyList.value?.get(1))
    }

    @Test
    fun testCurrencyCostList() {
        Assert.assertEquals(6, viewModel.currencyCosts.size)
    }

    @Test
    fun testCostCalculation() {
        viewModel.calculateCost()
        Assert.assertEquals(1.0,viewModel.result.value)

        viewModel.calculateCostModel.to = 1
        viewModel.calculateCost()
        Assert.assertEquals(1.0619395047259943,viewModel.result.value)

        viewModel.calculateCostModel.to = 2
        viewModel.calculateCost()
        Assert.assertEquals(3.214289126391684,viewModel.result.value)

        viewModel.calculateCostModel.to = 3
        viewModel.calculateCost()
        Assert.assertEquals(12.930758133550738,viewModel.result.value)

        viewModel.calculateCostModel.to = 4
        viewModel.calculateCost()
        Assert.assertNotEquals(12.930758133550738,viewModel.result.value)
    }
}