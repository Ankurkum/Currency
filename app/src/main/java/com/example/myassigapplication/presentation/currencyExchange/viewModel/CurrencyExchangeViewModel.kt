package com.example.myassigapplication.presentation.currencyExchange.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.domain.repository.CurrencyCostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class CurrencyExchangeViewModel @Inject constructor(
    private val currencyCostRepository: CurrencyCostRepository
) : ViewModel() {

    var currencyCosts: HashMap<String, Double> = HashMap()
    var calculateCostModel: CalculateCost = CalculateCost()

    val notifyList = MutableLiveData<Int>()

    private val _result = MutableLiveData<Double>()
    val result: LiveData<Double>
        get() = _result

    private val _baseCurrencyList = MutableLiveData<Array<String>>()
    val baseCurrencyList: LiveData<Array<String>>
        get() = _baseCurrencyList


    init {
        viewModelScope.launch {
            currencyCostRepository.getCurrencySymbols().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.symbols?.sort()
                        _baseCurrencyList.value = result.data?.symbols?.map { it }?.toTypedArray()
                    }
                    is Resource.Error -> {

                    }
                }
            }

            currencyCostRepository.getCurrencyExchangeRates().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        currencyCosts = result.data?.rates!!
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }

    fun onSwapClick() {
        val from = calculateCostModel.from
        calculateCostModel.from = calculateCostModel.to
        calculateCostModel.to = from
        notifyList.value = 0
    }

    fun calculateCost() {
        if (currencyCosts.containsKey(baseCurrencyList.value?.get(calculateCostModel.to)) && currencyCosts.containsKey(baseCurrencyList.value?.get(calculateCostModel.from))) {
            if (baseCurrencyList.value?.get(calculateCostModel.from).equals("EUR")) {
                calculateForEUR(calculateCostModel)
            } else {
                calculateForOtherCurr(calculateCostModel)
            }
        }
    }

    private fun calculateForEUR(calculateCost: CalculateCost) {
        val result =
            calculateCost.value * currencyCosts[baseCurrencyList.value?.get(calculateCost.to)]!!
        _result.value = result
    }

    private fun calculateForOtherCurr(calculateCost: CalculateCost) {
        val exchangeRate = currencyCosts[baseCurrencyList.value?.get(calculateCost.to)]?.div(
            currencyCosts[baseCurrencyList.value?.get(calculateCost.from)]!!
        )
        _result.value = exchangeRate?.times(calculateCost.value)

    }

    data class CalculateCost(var from: Int = 0, var to: Int = 0, var value: Double = 1.0)

}