package com.example.myassigapplication.domain.model

import com.example.myassigapplication.data.dto.ResponseErrorModel

data class DomainCurrencySymbolsData(
    val symbols: ArrayList<String> = ArrayList(),
    val error: ResponseErrorModel = ResponseErrorModel()
)
