package com.financetools.currencyrates

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.financetools.currencyrates.`interface`.EndpointInterface
import com.financetools.currencyrates.adapter.CurrenciesAdapter
import com.financetools.currencyrates.models.AllRatesApiResponse
import com.financetools.currencyrates.network.RetrofitService
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@SuppressLint("WrongConstant")
class ForexRatesView :ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var service : EndpointInterface
    var adapterList : MutableList<Pair<String, String>> = mutableListOf()
    var adapter: CurrenciesAdapter
    var recyclerView : RecyclerView
    var refreshIcon : ImageView
    var lastUpdateTime = 0

    init {
        val viewGroup = ViewGroup.inflate(context, R.layout.currency_rates_layout, this)

        recyclerView = viewGroup.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(viewGroup.context, LinearLayout.VERTICAL, false)
        adapter = CurrenciesAdapter(adapterList)
        recyclerView.adapter = adapter
        service = RetrofitService.retrofit
        refreshIcon = viewGroup.findViewById(R.id.refresh_icon) as ImageView
        refreshIcon.setOnClickListener {
            getCurrencyRates()
        }
    }

    private fun getCurrencyRates() {
        val currentTime = (System.currentTimeMillis()/1000)
        //10 seconds minimum interval
        if((currentTime - lastUpdateTime)>10) {
            val valueAnimator = ValueAnimator.ofFloat(0f, 360f)
            valueAnimator.addUpdateListener {
                val value = it.animatedValue as Float
                refreshIcon.rotation = value
            }
            valueAnimator.setDuration(1000).start()

            val call = service.getExchangeRate()
            call.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        updateRates(it)
                    }
            lastUpdateTime = (System.currentTimeMillis() / 1000).toInt()
        }
    }

    override fun onFinishInflate() {
        adapter.notifyDataSetChanged()
        super.onFinishInflate()
    }

    override fun onDraw(canvas: Canvas?) {
        //draw the View
    }

    private fun updateRates(response: AllRatesApiResponse){
        val parser = JsonParser()
        val element: JsonElement = parser.parse(response.rates.toString())
        val obj = element.asJsonObject
        val entries = obj.entrySet()

        adapterList.clear()
        for (entry in entries){
            val value:  Float? = (1/entry.value.toString().toFloat()) //as Float
            val str = value.toString()
            val pair = Pair(entry.key.toString(), str)
            adapterList.add(pair)
        }

        adapter.notifyDataSetChanged()
    }
}




