package com.upai.commonnetworkdemo.okhttp

import com.upai.commonnetworkdemo.util.Result

interface OkHttpView {

    fun showToast(msg: String)

    fun showProgress()

    fun hideProgress()

    fun updateRecyclerView(list: List<Result>)

}