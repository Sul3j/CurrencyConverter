package com.example.currency_exchanger

import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {
    @GET("api/exchangerates/tables/a?format=json")
    fun getCurrencyRate(): Call<List<CurrencyTable>>
}