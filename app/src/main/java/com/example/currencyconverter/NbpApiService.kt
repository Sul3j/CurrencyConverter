package com.example.currencyconverter

import retrofit2.Call
import retrofit2.http.GET

interface NbpApiService {
    @GET("endpoint_do_pobierania_kursow")
    fun getCurrencies(): Call<List<Currency>>
}