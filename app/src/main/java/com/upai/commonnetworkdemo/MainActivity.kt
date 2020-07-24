package com.upai.commonnetworkdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.upai.commonnetworkdemo.okhttp.OkHttpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        respondToClick()
    }

    private fun respondToClick() {
        btnOkHttp.setOnClickListener { toOkHttpActivity() }
    }

    private fun toOkHttpActivity() {
        startActivity(OkHttpActivity.getIntent(this))
    }

}
