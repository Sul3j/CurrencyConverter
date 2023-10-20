package com.example.currencyconverter

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class CurrencyEntity(
    @PrimaryKey
    val code: String,
    val name: String,
    val exchangeRate: Double,
    val timestamp: Long
)

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM CurrencyEntity")
    fun getAllCurrencies(): LiveData<List<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currencies: List<CurrencyEntity>)
}