package com.example.currency_exchanger

import java.util.Objects

data class CurrencyTable(
    val table: String,
    val rates: ArrayList<CurrencyRate>
)

data class CurrencyRate(
    val currency: String,
    val code: String,
    val mid: Double
)

//data class Rates(
//    val currency: String,
//    val code: String,
//    val mid: Float,
//)
//
//data class Currencies(
//    val table: String,
//    val no: String,
//    val effectiveDate: String,
//    val rates: ArrayList<Rates>,
//)

