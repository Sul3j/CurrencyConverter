package com.example.currencyconverter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class CurrencyViewModel(application: Application, private val apiService: NbpApiService) : AndroidViewModel(application) {
    private val currencyDao: CurrencyDao
    private val controller: CurrencyController
    val allCurrencies: LiveData<List<CurrencyEntity>>

    init {
        val database = AppDatabase.getDatabase(application)
        currencyDao = database.currencyDao()
        controller = CurrencyController(currencyDao, apiService)
        allCurrencies = currencyDao.getAllCurrencies()
    }

    fun fetchAndSaveCurrencies() {
        controller.fetchAndSaveCurrencies()
    }
}