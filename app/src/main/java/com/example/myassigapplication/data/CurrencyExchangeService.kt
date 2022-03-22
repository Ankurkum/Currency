package com.example.myassigapplication.data

import com.example.myassigapplication.data.dto.CurrencyExchangeRates
import com.example.myassigapplication.data.dto.CurrencySymbols
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyExchangeService {

    @GET("symbols")
    suspend fun getCurrencySymbols(@Query("access_key") access_key: String): CurrencySymbols

    @GET("latest")
    suspend fun getCurrencyExchangeRates(@Query("access_key") access_key: String): CurrencyExchangeRates

    @GET("{date}")
    suspend fun getHistoricalRates(@Path("date") date: String, @Query("access_key") access_key: String,
                           @Query("symbols") symbols: String, @Query("base") base: String): CurrencyExchangeRates

    companion object {
        const val BASE_URL = "http://data.fixer.io/api/"
    }
}