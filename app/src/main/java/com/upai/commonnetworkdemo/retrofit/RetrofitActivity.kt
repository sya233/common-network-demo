package com.upai.commonnetworkdemo.retrofit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.upai.commonnetworkdemo.R
import com.upai.commonnetworkdemo.util.HistoryAdapter
import com.upai.commonnetworkdemo.util.Result
import kotlinx.android.synthetic.main.activity_retrofit.*
import java.util.*

class RetrofitActivity : AppCompatActivity(), RetrofitView {

    // Presenter
    private lateinit var retrofitPresenter: RetrofitPresenter

    // Adapter
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        // 初始化
        init()
        // 向服务器请求数据
        val date = Calendar.getInstance()
        val month = date.get(Calendar.MONTH) + 1
        val day = date.get(Calendar.DAY_OF_MONTH)
        retrofitPresenter.requestDataFromServer(month, day)
    }

    private fun init() {
        // ActionBar
        supportActionBar?.title = "Retrofit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Presenter
        retrofitPresenter = RetrofitPresenter(this, RetrofitModel())
        // RecyclerView
        adapter = HistoryAdapter()
        rv_retrofit.layoutManager = LinearLayoutManager(rv_retrofit.context)
        rv_retrofit.adapter = adapter
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, RetrofitActivity::class.java)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showProgress() {
        runOnUiThread {
            pb_progress.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        runOnUiThread {
            pb_progress.visibility = View.GONE
        }
    }

    override fun updateRecyclerView(list: List<Result>) {
        runOnUiThread {
            adapter.updateList(list)
            adapter.notifyDataSetChanged()
        }
    }

}