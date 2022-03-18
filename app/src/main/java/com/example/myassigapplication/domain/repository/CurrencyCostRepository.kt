package com.example.myassigapplication.domain.repository

import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.domain.model.CurrencyExchangeData
import com.example.myassigapplication.domain.model.DomainCurrencySymbolsData
import kotlinx.coroutines.flow.Flow

interface CurrencyCostRepository {
    fun getCurrencySymbols(): Flow<Resource<DomainCurrencySymbolsData>>
    fun getCurrencyExchangeRates(): Flow<Resource<CurrencyExchangeData>>
    fun getHistoricalRates(date: String): Flow<Resource<CurrencyExchangeData>>
    fun getTopCurrencyRates(): Flow<Resource<CurrencyExchangeData>>
}