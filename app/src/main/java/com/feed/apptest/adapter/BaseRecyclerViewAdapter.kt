package com.feed.apptest.adapter

import androidx.recyclerview.widget.RecyclerView
import com.feed.apptest.listener.OnLoadMoreListener


abstract class BaseRecyclerViewAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    var isLoading = false
    var isMoreDataExists = true
    var loadMoreDataListener: OnLoadMoreListener? = null
    /*
        notifyDataSetChanged is final method so we can't override it
        call adapter.notifyDataChanged(); after update the list
    */
    fun notifyDataChanged() {
        notifyDataSetChanged()
        isLoading = false
    }

    fun setMoreDataAvailable(moreDataAvailable: Boolean) {
        this.isMoreDataExists = moreDataAvailable
    }

    fun setLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.loadMoreDataListener = loadMoreListener
    }

}
