package com.financetools.currencyrates.models

import com.google.gson.annotations.SerializedName

data class Currency (
    val str : String){
    @SerializedName("str")
    var rate : Float? = 0f
}