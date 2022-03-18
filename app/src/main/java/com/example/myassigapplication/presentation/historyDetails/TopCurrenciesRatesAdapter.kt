package com.example.myassigapplication.presentation.historyDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myassigapplication.R
import com.example.myassigapplication.domain.model.CurrencyDetailsData
import kotlinx.android.synthetic.main.item_exchange_rate.view.*

class TopCurrenciesRatesAdapter(private val mList: ArrayList<CurrencyDetailsData>) : RecyclerView.Adapter<TopCurrenciesRatesAdapter.ExchangeRateViewHolder>(){

    class ExchangeRateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {
        return ExchangeRateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_exchange_rate,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ExchangeRateViewHolder, position: Int) {
        val currency = mList[position]
            holder.itemView.apply {
                tvToCurrency.text = currency.currName
                tvConversionRate.text = currency.currRate.toString()
            }
    }

}