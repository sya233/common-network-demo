package com.upai.commonnetworkdemo.okhttp

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
import kotlinx.android.synthetic.main.activity_ok_http.*
import java.util.*

class OkHttpActivity : AppCompatActivity(), OkHttpView {

    private lateinit var okHttpPresenter: OkHttpPresenter

    // Adapter
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)
        // 初始化
        init();
        // 向服务器请求数据
        val date = Calendar.getInstance()
        val month = date.get(Calendar.MONTH) + 1
        val day = date.get(Calendar.DAY_OF_MONTH)
        okHttpPresenter.requestDataFromServer(month, day)
    }

    private fun init() {
        // ActionBar
        supportActionBar?.title = "OkHttp"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Presenter
        okHttpPresenter = OkHttpPresenter(this, OkHttpModel())
        // RecyclerView
        adapter = HistoryAdapter()
        rvOkHttp.layoutManager = LinearLayoutManager(rvOkHttp.context)
        rvOkHttp.adapter = adapter
    }

    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, OkHttpActivity::class.java)
            return intent
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showProgress() {
        runOnUiThread {
            pbProgress.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        runOnUiThread {
            pbProgress.visibility = View.GONE
        }
    }

    override fun updateRecyclerView(list: List<Result>) {
        runOnUiThread {
            adapter.updateList(list)
            adapter.notifyDataSetChanged()
        }
    }

}