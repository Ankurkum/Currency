package com.example.myassigapplication.data.dto

import com.example.myassigapplication.utils.Constants

data class ResponseErrorModel(
    val code: Int = 0,
    val type: String = Constants.EMPTY_STRING,
    val info: String = Constants.EMPTY_STRING
)
