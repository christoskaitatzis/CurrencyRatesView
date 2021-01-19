package com.financetools.currencyrates.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.financetools.currencyrates.ForexRatesView
import com.financetools.currencyrates.R

class SimpleActivity : AppCompatActivity() {

    private lateinit var currencyRatesView: ForexRatesView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.embed_activity)

        //binding the currencyRatesView
        currencyRatesView = findViewById(R.id.forex_view)
    }
}