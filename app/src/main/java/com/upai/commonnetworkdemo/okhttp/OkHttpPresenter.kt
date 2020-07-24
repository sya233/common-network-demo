package com.upai.commonnetworkdemo.okhttp

import com.google.gson.Gson
import com.upai.commonnetworkdemo.util.History
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class OkHttpPresenter(private val okHttpView: OkHttpView, val okHttpModel: OkHttpModel) {

    fun requestDataFromServer(month: Int, day: Int) {
        okHttpView.showProgress()
        okHttpModel.requestDataFromServer(month, day, object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                okHttpView.hideProgress()
                okHttpView.showToast("请求失败")
            }

            override fun onResponse(call: Call?, response: Response?) {
                okHttpView.hideProgress()
                if (response != null) {
                    val json: String = response.body().string()
                    val history: History =
                        Gson().fromJson(
                            json, History::class.java
                        )
                    okHttpView.updateRecyclerView(history.result)
                    okHttpView.showToast("请求成功")
                }
            }
        })
    }

}