package com.upai.commonnetworkdemo.retrofit

import android.util.Log
import com.upai.commonnetworkdemo.util.History
import com.upai.commonnetworkdemo.util.key
import com.upai.commonnetworkdemo.util.version
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class RetrofitPresenter(
    private val retrofitView: RetrofitView,
    private val retrofitModel: RetrofitModel
) {

    fun requestDataFromServer(month: Int, day: Int) {
        retrofitView.showProgress()
        val retrofit = retrofitModel.createService(RestApi::class.java)
        retrofit.getData(version, month.toString(), day.toString(), key).enqueue(
            object : Callback<History> {
                override fun onFailure(call: Call<History>, t: Throwable) {
                    retrofitView.hideProgress()
                    retrofitView.showToast("请求失败")
                }

                override fun onResponse(call: Call<History>, response: Response<History>) {
                    retrofitView.hideProgress()
                    val history: History? = response.body()
                    if (history != null) {
                        retrofitView.updateRecyclerView(history.result)
                        retrofitView.showToast("请求成功")
                    } else {
                        Log.d("RetrofitP", "history是null")
                    }
                }

            }
        )
    }

    interface RestApi {
        @FormUrlEncoded
        @POST("toh")
        fun getData(
            @Field("v") v: String,
            @Field("month") month: String,
            @Field("day") day: String,
            @Field("key") key: String
        ): Call<History>
    }

}