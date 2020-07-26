package com.upai.commonnetworkdemo.retrofit

import com.upai.commonnetworkdemo.util.address
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModel {

    private val client: OkHttpClient = OkHttpClient.Builder().build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("$address/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }

}