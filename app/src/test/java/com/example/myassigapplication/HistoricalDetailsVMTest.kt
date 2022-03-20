package com.example.myassigapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myassigapplication.presentation.historyDetails.viewModel.HistoricalDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HistoricalDetailsVMTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HistoricalDetailsViewModel

    @Mock
    private lateinit var fakeCurrencyRepository: FakeCurrencyRepository

    @Before
    fun setUp(){
        `when`(fakeCurrencyRepository.getHistoricalRates(anyString())).thenReturn(JsonData.getExchangeRatesWithFlow())
        `when`(fakeCurrencyRepository.getTopCurrencyRates()).thenReturn(JsonData.getExchangeRatesWithFlow())

        viewModel = HistoricalDetailsViewModel(fakeCurrencyRepository)
    }

    @Test
    fun testCurrencyList() {
        Assert.assertEquals(21, viewModel.setAdapter.value?.size)
        Assert.assertEquals(16.241446, viewModel.setAdapter.value?.get(2)?.currDetailsData?.currRate)
    }

    @Test
    fun testCurrencyNames() {
        Assert.assertEquals("AUD", viewModel.setTopCurrRatesRecyclerView.value?.get(0)?.currName)
        Assert.assertEquals("EUR", viewModel.setTopCurrRatesRecyclerView.value?.get(3)?.currName)
    }

    @Test
    fun testCurrencyCostList() {
        Assert.assertEquals(6, viewModel.setTopCurrRatesRecyclerView.value?.size)
        Assert.assertEquals(4.157385, viewModel.setTopCurrRatesRecyclerView.value?.get(2)?.currRate)
    }

}