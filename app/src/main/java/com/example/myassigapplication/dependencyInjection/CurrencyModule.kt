package com.example.myassigapplication.dependencyInjection

import com.example.myassigapplication.data.CurrencyExchangeService
import com.example.myassigapplication.data.repository.CurrencyRepositoryImplementation
import com.example.myassigapplication.domain.repository.CurrencyCostRepository
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