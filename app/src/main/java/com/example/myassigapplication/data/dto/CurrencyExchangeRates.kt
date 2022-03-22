package com.example.myassigapplication.data.dto

import com.example.myassigapplication.domain.model.CurrencyExchangeData
import com.example.myassigapplication.utils.Constants

data class CurrencyExchangeRates(
    val base: String = Constants.EMPTY_STRING,
    val date: String = Constants.EMPTY_STRING,
    val rates: HashMap<String, Double> = HashMap(),
    val success: Boolean = false,
    val timestamp: Int = 0,
    val error: ResponseErrorModel = ResponseErrorModel()
) {
    fun toCurrencyExchange(): CurrencyExchangeData {
        return CurrencyExchangeData(
            base, date, rates, success, timestamp, error
        )
    }
}