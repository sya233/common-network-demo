package com.upai.commonnetworkdemo.util

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

fun sendOkHttpRequest(month: Int, day: Int, callback: okhttp3.Callback) {
    val client = OkHttpClient()

    val requestBody = FormBody.Builder().add("v", version).add("month", month.toString())
        .add("day", day.toString()).add("key", key).build()

    val request = Request.Builder().url(address).post(requestBody).build()
    client.newCall(request).enqueue(callback)

}