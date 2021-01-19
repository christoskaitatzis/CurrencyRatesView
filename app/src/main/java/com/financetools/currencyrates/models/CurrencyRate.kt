package com.financetools.currencyrates.models

import com.google.gson.annotations.SerializedName

data class CurrencyRate (
    @SerializedName("USD")
    var usdRate : Float,
    @SerializedName("GBP")
    var gbpRate : Float
)