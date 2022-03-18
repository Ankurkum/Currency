package com.example.myassigapplication.domain.use_case

import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.domain.model.CurrencyExchangeData
import com.example.myassigapplication.domain.repository.CurrencyCostRepository
import kotlinx.coroutines.flow.Flow

class GetCurrencyExchangeRates (private val repo: CurrencyCostRepository) {

    operator fun invoke(): Flow<Resource<CurrencyExchangeData>> {
        return repo.getCurrencyExchangeRates()

    }
}