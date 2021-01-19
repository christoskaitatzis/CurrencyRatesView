package com.financetools.currencyrates.models

import com.google.gson.annotations.SerializedName

data class ExchangeRatesApiResponse(
    @SerializedName("rates")
    var rates : CurrencyRate,
    @SerializedName("base")
    var base : String,
    @SerializedName("date")
    var date : String
)