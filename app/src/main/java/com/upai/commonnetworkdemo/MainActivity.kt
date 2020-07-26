package com.upai.commonnetworkdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.upai.commonnetworkdemo.okhttp.OkHttpActivity
import com.upai.commonnetworkdemo.retrofit.RetrofitActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        respondToClick()
    }

    private fun respondToClick() {
        btnOkHttp.setOnClickListener { toOkHttpActivity() }
        btn_retrofit.setOnClickListener { toRetrofitActivity() }
    }

    private fun toOkHttpActivity() {
        startActivity(OkHttpActivity.getIntent(this))
    }

    private fun toRetrofitActivity() {
        startActivity(RetrofitActivity.getIntent(this))
    }

}
