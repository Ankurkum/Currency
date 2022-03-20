package com.example.myassigapplication

import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.domain.model.CurrencyExchangeData
import com.example.myassigapplication.domain.model.DomainCurrencySymbolsData
import com.example.myassigapplication.domain.repository.CurrencyCostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class FakeCurrencyRepository: CurrencyCostRepository {
    override fun getCurrencySymbols(): Flow<Resource<DomainCurrencySymbolsData>> = flow {
    }

    override fun getCurrencyExchangeRates(): Flow<Resource<CurrencyExchangeData>> = flow {
    }

    override fun getHistoricalRates(date: String): Flow<Resource<CurrencyExchangeData>> = flow {
    }

    override fun getTopCurrencyRates(): Flow<Resource<CurrencyExchangeData>> = flow {
    }
}