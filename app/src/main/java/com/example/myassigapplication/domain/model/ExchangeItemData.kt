package com.example.myassigapplication.domain.model

import com.example.myassigapplication.utils.Constants

data class ExchangeItemData(
    val viewType: Int = 0,
    val currDetailsData: CurrencyDetailsData = CurrencyDetailsData(),
    val date: String = Constants.EMPTY_STRING
)
