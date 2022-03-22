package com.example.myassigapplication.data.repository

import com.example.myassigapplication.BuildConfig
import com.example.myassigapplication.core.Resource
import com.example.myassigapplication.data.CurrencyExchangeService
import com.example.myassigapplication.data.dto.CurrencyExchangeRates
import com.example.myassigapplication.data.dto.CurrencySymbols
import com.example.myassigapplication.domain.model.CurrencyExchangeData
import com.example.myassigapplication.domain.model.DomainCurrencySymbolsData
import com.example.myassigapplication.domain.repository.CurrencyCostRepository
import com.example.myassigapplication.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CurrencyRepositoryImplementation (
    private val exchangeService: CurrencyExchangeService
): CurrencyCostRepository  {
    override fun getCurrencySymbols(): Flow<Resource<DomainCurrencySymbolsData>> = flow {
        when (val response: Any = getResponse(ApiType.CURRENCIES)) {
            is String -> { emit(Resource.Error(response)) }
            is CurrencySymbols -> {
                val sym = response.toCurrencySymbols()
                if (response.success) {
                    emit(Resource.Success(sym))
                } else {
                    emit(Resource.Error(message = sym.error.info))
                }
            }
        }
    }

    override fun getCurrencyExchangeRates(): Flow<Resource<CurrencyExchangeData>> = flow {
        when(val response: Any = getResponse(ApiType.CURR_EXCHANGE_RATES)){
            is String -> {
                emit(Resource.Error(response))
            }
            is CurrencyExchangeRates -> {
                val sym = response.toCurrencyExchange()
                if(response.success){
                    emit(Resource.Success(sym))
                } else {
                    emit(Resource.Error(message = sym.error.info))
                }
            }
        }
    }

    override fun getHistoricalRates(date: String, baseCurrency: String, convertCurrency: String): Flow<Resource<CurrencyExchangeData>> = flow {
        when(val response: Any = getResponse(ApiType.HISTORICAL_RATES, date, baseCurrency, convertCurrency)){
            is String -> { emit(Resource.Error(response)) }
            is CurrencyExchangeRates -> {
                val sym = response.toCurrencyExchange()
                if(response.success){
                    emit(Resource.Success(sym))
                } else {
                    emit(Resource.Error(message = sym.error.info))
                }
            }
        }
    }

    override fun getTopCurrencyRates(date: String, baseCurrency: String, convertCurrency: String): Flow<Resource<CurrencyExchangeData>> = flow {
        when(val response: Any = getResponse(ApiType.HISTORICAL_RATES, date, baseCurrency, convertCurrency)){
            is String -> { emit(Resource.Error(response)) }
            is CurrencyExchangeRates -> {
                val sym = response.toCurrencyExchange()
                if(response.success){
                    emit(Resource.Success(sym))
                } else {
                    emit(Resource.Error(message = sym.error.info))
                }
            }
        }
    }

    private suspend fun getResponse(type: ApiType,
                                        date: String = Constants.EMPTY_STRING,
                                        baseCurrency: String= Constants.EMPTY_STRING,
                                        convertCurrency: String=Constants.EMPTY_STRING): Any {
        val response: Any
        with(exchangeService) {
            try {
                response = when (type) {
                    ApiType.CURRENCIES -> { getCurrencySymbols(BuildConfig.ACCESS_KEY) }
                    ApiType.CURR_EXCHANGE_RATES -> { getCurrencyExchangeRates(BuildConfig.ACCESS_KEY) }
                    ApiType.HISTORICAL_RATES -> {
                        getHistoricalRates(date, BuildConfig.ACCESS_KEY, convertCurrency, baseCurrency)
                    }
                }
            } catch (e: HttpException) {
                return "Oops, something went wrong!"
            } catch (e: IOException) {
                return "Couldn't reach server, check your internet connection."
            }
        }
        return response
    }

    enum class ApiType {
        CURRENCIES, CURR_EXCHANGE_RATES, HISTORICAL_RATES
    }
}