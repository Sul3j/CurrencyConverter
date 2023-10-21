package com.example.currencyconverter

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currency: List<CurrencyEntity>)

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): LiveData<List<CurrencyEntity>>
}