package com.example.myassigapplication.presentation.historyDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.domain.model.CurrencyDetailsData
import com.example.myassigapplication.data.dto.ExchangeItemData
import com.example.myassigapplication.domain.use_case.GetHistoricalRates
import com.example.myassigapplication.domain.use_case.GetTopCurrencyRates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HistoricalDetailsViewModel @Inject constructor(private val getHistoricalRates: GetHistoricalRates, private val getTopCurrencyRates: GetTopCurrencyRates): ViewModel() {

    private val _setAdapter = MutableLiveData<ArrayList<ExchangeItemData>>()
    val setAdapter: LiveData<ArrayList<ExchangeItemData>>
        get() = _setAdapter

    private val _setTopCurrRatesRecyclerView = MutableLiveData<ArrayList<CurrencyDetailsData>>()
    val setTopCurrRatesRecyclerView: LiveData<ArrayList<CurrencyDetailsData>>
        get() = _setTopCurrRatesRecyclerView

    init {
        val items = ArrayList<ExchangeItemData>()
        val topCurrItems = ArrayList<CurrencyDetailsData>()
        viewModelScope.launch {
            coroutineScope {

                val call1 = async { getHistoricalRates(LocalDate.now().toString()) }
                val call2 = async { getHistoricalRates(LocalDate.now().minusDays(1L).toString())}
                val call3 = async { getHistoricalRates(LocalDate.now().minusDays(2L).toString()) }
                val call4 = async { getTopCurrencyRates() }

                val response1 = call1.await()
                val response2 = call2.await()
                val response3 = call3.await()
                val response4 = call4.await()

                response1.onEach {
                    when(it) {
                        is Resource.Success -> {
                            items.add(ExchangeItemData(0, date = it.data!!.date))
                            for(set:Map.Entry<String, Double> in it.data.rates) {
                                items.add(ExchangeItemData(1, CurrencyDetailsData(currName = set.key, currRate = set.value)))
                            }
                        }
                        is Resource.Error -> {
                        }
                    }
                }.launchIn(this)

                response2.onEach {
                    when(it) {
                        is Resource.Success -> {
                            items.add(ExchangeItemData(0, date = it.data!!.date))
                            for(set:Map.Entry<String, Double> in it.data.rates) {
                                items.add(ExchangeItemData(1,CurrencyDetailsData(currName = set.key, currRate = set.value)))
                            }
                        }
                        is Resource.Error -> {
                        }
                    }
                }.launchIn(this)

                response3.onEach {
                    when(it) {
                        is Resource.Success -> {
                            items.add(ExchangeItemData(0, date = it.data!!.date))
                            for(set:Map.Entry<String, Double> in it.data.rates) {
                                items.add(ExchangeItemData(1,CurrencyDetailsData(currName = set.key, currRate = set.value)))
                            }
                            withContext(Dispatchers.Main){
                                setAdapter(items)
                            }
                        }
                        is Resource.Error -> {
                        }
                    }
                }.launchIn(this)

                response4.onEach {
                    when (it) {
                        is Resource.Success -> {
                            for (set: Map.Entry<String, Double> in it.data!!.rates) {
                                topCurrItems.add(CurrencyDetailsData(currName = set.key, currRate = set.value))
                            }
                            withContext(Dispatchers.Main){
                                setTopCurrRatesRecyclerView(topCurrItems)
                            }

                        }
                        is Resource.Error -> {
                        }
                    }
                }.launchIn(this)
            }
        }

    }

    private fun setAdapter(itemData: ArrayList<ExchangeItemData>){
        _setAdapter.value = itemData
    }

    private fun setTopCurrRatesRecyclerView(items: ArrayList<CurrencyDetailsData>){
        _setTopCurrRatesRecyclerView.value = items
    }
}