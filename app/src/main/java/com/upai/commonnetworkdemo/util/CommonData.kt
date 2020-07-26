package com.upai.commonnetworkdemo.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.upai.commonnetworkdemo.R
import kotlinx.android.synthetic.main.rv_item_ok_http.view.*

const val version = "1.0"

const val key = "0d696f99614d80195334a654faa9fc66"

const val address = "http://api.juheapi.com/japi"

// bean
data class History(
    val result: List<Result>,
    val reason: String,
    val error_code: String
)

data class Result(
    val _id: String,
    val title: String,
    val pic: String,
    val year: String,
    val month: String,
    val day: String,
    val des: String,
    val lunar: String
)

// RecyclerView
class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.Holder>() {

    var historyList: List<Result> = arrayListOf()

    fun updateList(list: List<Result>) {
        historyList = list
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.civPic.context).load(historyList[position].pic)
            .into(holder.itemView.civPic)
        holder.itemView.tvTitle.text = historyList[position].title
        val time: String =
            "${historyList[position].year}-${historyList[position].month}-${historyList[position].day}"
        holder.itemView.tvTime.text = time
        holder.itemView.tvDes.text = historyList[position].des
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item_ok_http,
            parent, false
        )
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}