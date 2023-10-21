package com.example.currencyconverter

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): LiveData<List<CurrencyEntity>>
}