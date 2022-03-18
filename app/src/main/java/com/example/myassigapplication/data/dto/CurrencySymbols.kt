package com.example.myassigapplication.data.dto

import com.example.myassigapplication.domain.model.DomainCurrencySymbolsData
import java.util.ArrayList

data class CurrencySymbols(
    val success: Boolean,
    val symbols: HashMap<String, String>
) {
    fun toCurrencySymbols(): DomainCurrencySymbolsData {
        val keysArray = ArrayList(symbols.keys)
        return DomainCurrencySymbolsData(keysArray)
    }
}