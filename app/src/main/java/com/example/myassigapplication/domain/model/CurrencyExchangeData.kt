package com.example.myassigapplication.domain.model

import com.example.myassigapplication.data.dto.ResponseErrorModel
import com.example.myassigapplication.utils.Constants

data class CurrencyExchangeData(
    val base: String = Constants.EMPTY_STRING,
    val date: String = Constants.EMPTY_STRING,
    val rates: HashMap<String, Double> = HashMap(),
    val success: Boolean = false,
    val timestamp: Int = 0,
    val error: ResponseErrorModel = ResponseErrorModel()
)
