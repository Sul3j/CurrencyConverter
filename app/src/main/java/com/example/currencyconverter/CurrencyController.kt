package com.example.currencyconverter


import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyController(private val currencyDao: CurrencyDao, private val apiService: NbpApiService) {
    fun fetchAndSaveCurrencies() {
        val call = apiService.getCurrencies()
        call.enqueue(object : Callback<List<Currency>> {
            override fun onResponse(call: Call<List<Currency>>, response: Response<List<Currency>>) {
                if (response.isSuccessful) {
                    val currencies = response.body()
                    val currentTimeMillis = System.currentTimeMillis()

                    if (currencies != null) {
                        val currencyEntities = currencies.map { currency ->
                            CurrencyEntity(
                                code = currency.code,
                                name = currency.name,
                                exchangeRate = currency.exchangeRate,
                                timestamp = currentTimeMillis
                            )
                        }
                        currencyDao.insertCurrencies(currencyEntities)
                    }
                } else {
                    // Obsłuż błąd pobierania danych z serwera
                }
            }

            override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
                // Obsłuż błąd komunikacji z serwerem
            }
        })
    }

    fun getCurrenciesFromDatabase(): LiveData<List<CurrencyEntity>> {
        return currencyDao.getAllCurrencies()
    }
}