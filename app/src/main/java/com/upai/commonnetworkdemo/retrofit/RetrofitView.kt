package com.upai.commonnetworkdemo.retrofit

import com.upai.commonnetworkdemo.util.Result

interface RetrofitView {

    fun showToast(msg: String)

    fun showProgress()

    fun hideProgress()

    fun updateRecyclerView(list: List<Result>)

}