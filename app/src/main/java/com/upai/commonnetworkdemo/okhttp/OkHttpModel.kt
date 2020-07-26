package com.upai.commonnetworkdemo.okhttp

import com.upai.commonnetworkdemo.util.address
import com.upai.commonnetworkdemo.util.key
import com.upai.commonnetworkdemo.util.version
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class OkHttpModel {

    fun requestDataFromServer(month: Int, day: Int, callback: okhttp3.Callback) {
        val client = OkHttpClient()

        val requestBody = FormBody.Builder().add("v", version).add("month", month.toString())
            .add("day", day.toString()).add(
                "key", key
            ).build()

        val request = Request.Builder().url("$address/toh").post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

}