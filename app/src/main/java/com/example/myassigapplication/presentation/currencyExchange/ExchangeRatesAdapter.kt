package com.example.myassigapplication.presentation.currencyExchange

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myassigapplication.R
import com.example.myassigapplication.domain.model.ExchangeItemData
import kotlinx.android.synthetic.main.item_exchange_rate.view.*
import kotlinx.android.synthetic.main.item_header.view.*

class ExchangeRatesAdapter(private val mList: ArrayList<ExchangeItemData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TYPE_HEADER : Int = 0
    private val TYPE_LIST : Int = 1

    class ExchangeRateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if(mList[position].viewType == 0)
        {
            return TYPE_HEADER
        }
        return TYPE_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_HEADER) {
            return HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_header,
                    parent,
                    false
                )
            )
        }
        return ExchangeRateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_exchange_rate,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currency = mList[position]
        if(holder is HeaderViewHolder)
        {
            holder.itemView.tvDate.text = currency.date
        }
        if(holder is ExchangeRateViewHolder)
        {
            holder.itemView.apply {
                tvToCurrency.text = currency.currDetailsData.currName
                tvConversionRate.text = currency.currDetailsData.currRate.toString()
            }
        }
    }

}