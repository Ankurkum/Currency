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

    //for testing
    @GET("62347ad206182767437a92cf")
    suspend fun getCurrencySymbols(): CurrencySymbols

    @GET("6234651b06182767437a877e/1")
    suspend fun getHistoricalRates(): CurrencyExchangeRates
    //for testing --

    companion object {
        const val BASE_URL = "http://data.fixer.io/api/"
        const val ACCESS_KEY = "49b2400c7fc3e8e25de8e9f5a6f1647a"
    }
}