package com.example.currency_exchanger

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private lateinit var currencyApi: CurrencyApi
    private lateinit var recyclerView: RecyclerView
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var refreshButton: Button
    private lateinit var errorText: TextView

    private val BASE_URL = "http://api.nbp.pl/"
    private val TAG: String = "CHECK_RESPONSE"

    var currencyRates: ArrayList<CurrencyRate> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        currencyAdapter = CurrencyAdapter()
        recyclerView.adapter = currencyAdapter
        errorText = findViewById(R.id.errorText)

        refreshButton = findViewById(R.id.refreshButton)
        refreshButton.setOnClickListener { getCurrencyRates() }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.nbp.pl")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        currencyApi = retrofit.create(CurrencyApi::class.java)

        getCurrencyRates()
    }

    private fun getCurrencyRates() {
        currencyApi.getCurrencyRate().enqueue(object : Callback<List<CurrencyTable>>{
            override fun onResponse(
                call: Call<List<CurrencyTable>>,
                response: Response<List<CurrencyTable>>
            ) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        currencyRates = it[0].rates
                        currencyAdapter.setCurrencyRates(currencyRates)
                        recyclerView.visibility = View.VISIBLE
                        errorText.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<List<CurrencyTable>>, t: Throwable) {
                Log.i(TAG, "failure: ${t.message}")
                recyclerView.visibility = View.GONE
                errorText.visibility = View.VISIBLE
            }

        })


    }
}
