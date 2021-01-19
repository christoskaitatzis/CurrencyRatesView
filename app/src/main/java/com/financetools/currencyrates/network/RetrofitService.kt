package com.financetools.currencyrates.network

import com.financetools.currencyrates.`interface`.EndpointInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private val client = OkHttpClient.Builder().build()

    val retrofit: EndpointInterface = Retrofit.Builder()
        .baseUrl("https://api.exchangeratesapi.io/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(EndpointInterface::class.java)
}