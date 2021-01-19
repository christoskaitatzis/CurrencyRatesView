package com.financetools.currencyrates.`interface`

import com.financetools.currencyrates.models.AllRatesApiResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface EndpointInterface {

    @GET("latest")
    fun getExchangeRate() : Observable<AllRatesApiResponse>
}