package com.example.currency_exchanger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    private var currencyRates: List<CurrencyRate> = listOf()

    fun setCurrencyRates(rates: List<CurrencyRate>) {
        currencyRates = rates
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val rate = currencyRates[position]
        holder.bind(rate)
    }

    override fun getItemCount(): Int {
        return currencyRates.size
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val currencyTextView: TextView = itemView.findViewById(R.id.currencyTextView)
        private val codeTextView: TextView = itemView.findViewById(R.id.codeTextView)
        private val midTextView: TextView = itemView.findViewById(R.id.midTextView)

        fun bind(rate: CurrencyRate) {
            currencyTextView.text = rate.currency
            codeTextView.text = rate.code
            midTextView.text = rate.mid.toString()
        }
    }
}