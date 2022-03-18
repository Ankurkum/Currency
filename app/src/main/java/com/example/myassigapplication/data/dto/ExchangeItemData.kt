package com.example.myassigapplication.data.dto

import com.example.myassigapplication.domain.model.CurrencyDetailsData

data class ExchangeItemData(
    val viewType: Int =0,
    val currDetailsData: CurrencyDetailsData = CurrencyDetailsData(),
    val date: String =""
)
