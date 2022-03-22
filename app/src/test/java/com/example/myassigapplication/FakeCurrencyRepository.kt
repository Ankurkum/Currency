package com.example.myassigapplication

import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.domain.model.CurrencyExchangeData
import com.example.myassigapplication.domain.model.DomainCurrencySymbolsData
import com.example.myassigapplication.domain.repository.CurrencyCostRepository
import kotlinx.coroutines.flow.Flow

open class FakeCurrencyRepository: CurrencyCostRepository {
    override fun getCurrencySymbols(): Flow<Resource<DomainCurrencySymbolsData>> {
        return JsonData.getCurrencySymbolsWithFlow()
    }

    override fun getCurrencyExchangeRates(): Flow<Resource<CurrencyExchangeData>> {
        return JsonData.getCurrExchangeRatesWithFlow()
    }

    override fun getHistoricalRates(
        date: String,
        baseCurrency: String,
        convertCurrency: String
    ): Flow<Resource<CurrencyExchangeData>> {
        return JsonData.getExchangeRatesWithFlow()
    }

    override fun getTopCurrencyRates(
        date: String,
        baseCurrency: String,
        convertCurrency: String
    ): Flow<Resource<CurrencyExchangeData>> {
        return JsonData.getExchangeRatesWithFlow()
    }

}