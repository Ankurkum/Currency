package com.example.myassigapplication.dependencyInjection

import com.example.myassigapplication.data.CurrencyExchangeService
import com.example.myassigapplication.data.repository.CurrencyRepositoryImplementation
import com.example.myassigapplication.domain.repository.CurrencyCostRepository
import com.example.myassigapplication.domain.use_case.GetCurrencySymbols
import com.example.myassigapplication.domain.use_case.GetCurrencyExchangeRates
import com.example.myassigapplication.domain.use_case.GetHistoricalRates
import com.example.myassigapplication.domain.use_case.GetTopCurrencyRates
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {

    @Provides
    @Singleton
    fun provideGetCurrencyConvertedValue(repository: CurrencyCostRepository): GetCurrencySymbols {
        return GetCurrencySymbols(repository)
    }

    @Provides
    @Singleton
    fun provideGetCurrencyExchangeRates(repository: CurrencyCostRepository): GetCurrencyExchangeRates {
        return GetCurrencyExchangeRates(repository)
    }

    @Provides
    @Singleton
    fun provideGetHistoricalRates(repository: CurrencyCostRepository): GetHistoricalRates {
        return GetHistoricalRates(repository)
    }

    @Provides
    @Singleton
    fun provideGetTopCurrencyRates(repository: CurrencyCostRepository): GetTopCurrencyRates {
        return GetTopCurrencyRates(repository)
    }

    @Provides
    @Singleton
    fun provideCurrencyInfoImpl(exchangeService: CurrencyExchangeService): CurrencyCostRepository {
        return CurrencyRepositoryImplementation(exchangeService)
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): CurrencyExchangeService {
        return Retrofit.Builder()
            .baseUrl(CurrencyExchangeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyExchangeService::class.java)
    }
}