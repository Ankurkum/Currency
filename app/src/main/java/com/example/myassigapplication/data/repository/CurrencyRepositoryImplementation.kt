package com.example.myassigapplication.data.repository

import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.data.CurrencyExchangeService
import com.example.myassigapplication.domain.model.CurrencyExchangeData
import com.example.myassigapplication.domain.model.DomainCurrencySymbolsData
import com.example.myassigapplication.domain.repository.CurrencyCostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate

class CurrencyRepositoryImplementation (
    private val exchangeService: CurrencyExchangeService
): CurrencyCostRepository  {
    override fun getCurrencySymbols(): Flow<Resource<DomainCurrencySymbolsData>> = flow {
        val currencyInfo = exchangeService.getCurrencySymbols(CurrencyExchangeService.ACCESS_KEY)
        if(currencyInfo.success){
            val sym = currencyInfo.toCurrencySymbols()
            emit(Resource.Success(sym))
        } else {
            emit(Resource.Error("No Response"))
        }

    }

    override fun getCurrencyExchangeRates(): Flow<Resource<CurrencyExchangeData>> = flow {
        val currencyInfo = exchangeService.getCurrencyExchangeRates(CurrencyExchangeService.ACCESS_KEY)
        if(currencyInfo.success){
            val sym = currencyInfo.toCurrencyExchange()
            emit(Resource.Success(sym))
        } else {
            emit(Resource.Error("No Response"))
        }
    }

    override fun getHistoricalRates(date: String): Flow<Resource<CurrencyExchangeData>> = flow {
        val currencyInfo = exchangeService.getHistoricalRates(date,CurrencyExchangeService.ACCESS_KEY, arrayOf("USD", "AUD", "CAD", "PLN", "MXN").joinToString(), "EUR")
        if(currencyInfo.success){
            val sym = currencyInfo.toCurrencyExchange()
            emit(Resource.Success(sym))
        } else {
            emit(Resource.Error("No Response"))
        }
    }

    override fun getTopCurrencyRates(): Flow<Resource<CurrencyExchangeData>> = flow {
        val currencyInfo = exchangeService.getHistoricalRates(LocalDate.now().toString(), CurrencyExchangeService.ACCESS_KEY, arrayOf("USD", "AUD", "CAD", "PLN", "MXN", "AED", "AUD", "BMD", "CLP", "CNY").joinToString(), "EUR")
        if(currencyInfo.success){
            val sym = currencyInfo.toCurrencyExchange()
            emit(Resource.Success(sym))
        } else {
            emit(Resource.Error("No Response"))
        }
    }
}