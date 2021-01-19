package com.financetools.currencyrates.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.financetools.currencyrates.R

import java.util.Currency


class CurrenciesAdapter(private var itemsList: List<Pair<String, String>>) :
        RecyclerView.Adapter<CurrenciesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.label_text)
        var rateTextView: TextView = view.findViewById(R.id.currency_rate)
        var symbolTv: TextView = view.findViewById(R.id.symbol)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.currency_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        val label = "1 " + item.first + " ="
        val rate = item.second.toString()
        holder.itemTextView.text = label
        holder.rateTextView.text = rate + " EUR"
        holder.symbolTv.text = getSymbol(item.first)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    private fun getSymbol(isoStr: String): String? {
        var symbol = " "
        if (isoStr.length == 3) {
            val currency: Currency = Currency.getInstance(isoStr)
            symbol = currency.getSymbol()
        }
        return symbol
    }

}