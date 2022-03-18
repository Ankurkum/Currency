package com.example.myassigapplication.data.dto

import com.example.myassigapplication.domain.model.CurrencyExchangeData

data class CurrencyExchangeRates(
    val base: String,
    val date: String,
    val rates: HashMap<String, Double>,
    val success: Boolean,
    val timestamp: Int
) {
    fun toCurrencyExchange(): CurrencyExchangeData {
        return CurrencyExchangeData(
            base, date, rates, success, timestamp
        )
    }
}