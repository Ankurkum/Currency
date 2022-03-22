package com.example.myassigapplication.domain.model

import com.example.myassigapplication.utils.Constants

data class CurrencyDetailsData (
    val currName: String = Constants.EMPTY_STRING,
    val currRate: Double = 0.0
)
