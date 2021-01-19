package com.financetools.currencyrates.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class AllRatesApiResponse (
    @SerializedName("rates")
    var rates : JsonObject,
    @SerializedName("base")
    var base : String,
    @SerializedName("date")
    var date : String
)