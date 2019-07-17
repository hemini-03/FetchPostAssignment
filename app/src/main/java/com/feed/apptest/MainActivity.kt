package com.feed.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.feed.Utility.AppConst
import com.feed.Utility.isConnected
import com.feed.Utility.showToast
import com.feed.apptest.adapter.PostViewDataAdapter
import com.feed.apptest.listener.OnLoadMoreListener
import com.feed.apptest.model.PostModel
import com.feed.apptest.webcall.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_progressbar.progressBarLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var page = 1
    private var tag = "story"
    private var ndPage = 0
    private var isLoadMoreData = false
    private var isRefresh = false
    private var callRootBack: Call<PostModel>? = null
    private var hitModelList: MutableList<PostModel.HitModel> = mutableListOf()
    private var adapter: PostViewDataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbarTitle()
        initView()
        callFetchPostDataAPI()
    }

    private fun setToolbarTitle() {
        supportActionBar!!.title = resources.getString(R.string.app_name)
    }

    // Initialing View
    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        swipeRefreshLayout.setOnRefreshListener {
            callRootBack?.let {
                it.cancel()
            }
            isRefresh = true
            isLoadMoreData = false
            page = 1

            textViewNoInternet.visibility = View.GONE

            callFetchPostDataAPI()
        }

    }

    // Calling API
    private fun callFetchPostDataAPI() {
        callRootBack = RestClient(this).getApiService().searchData(tag, page)
        callRootBack?.enqueue(object : Callback<PostModel> {
            override fun onResponse(call: Call<PostModel>, response: Response<PostModel>) {
                if (response.code() == AppConst.SUCCESS_CODE) {
                    // For Manage pull to refresh
                    if (isRefresh) {

                        hitModelList.clear()
                        adapter?.let {
                            it.selectedItem.clear()
                            it.setMoreDataAvailable(false)
                            adapter = null
                            setToolbarTitle()
                            isRefresh = false
                        }
                    }
                    ndPage = response.body()?.nbPages!!
                    response.body()?.hits?.let {
                        hitModelList.addAll(it)
                    }

                    setUpSearchDataAdapter()
                }

                progressBarLoader.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<PostModel>, t: Throwable) {
                progressBarLoader.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                if (!isConnected()) {
                    if (hitModelList.size > 0) {
                        resources.getString(R.string.no_internet_connection).showToast(this@MainActivity)
                        textViewNoInternet.visibility = View.GONE

                    } else {
                        recyclerView.visibility = View.GONE
                        textViewNoInternet.visibility = View.VISIBLE
                    }

                }
            }

        })
    }


    // Set Up RecyclerView Adapter
    private fun setUpSearchDataAdapter() {
        if (!isLoadMoreData) {
            adapter = PostViewDataAdapter(this, hitModelList, onItemClick = ::onItemClick)
            recyclerView.adapter = adapter
            adapter?.setLoadMoreListener(object : OnLoadMoreListener {
                override fun onLoadMore() {
                    if (!isRefresh) {
                        page += 1

                        isLoadMoreData = true
                        callRootBack?.let {
                            it.cancel()
                        }
                        callFetchPostDataAPI()
                    }
                }

            })
        } else {
            if (ndPage == 0) {
                adapter?.setMoreDataAvailable(false)
            }
            adapter?.notifyDataChanged()
        }

        if (hitModelList.size > 0) {
            recyclerView.visibility = View.VISIBLE
            textViewNoInternet.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            textViewNoInternet.visibility = View.VISIBLE
        }
    }

    private fun onItemClick(position: Int) {
        if (adapter?.selectedItem?.contains(position)!!) {
            adapter?.selectedItem?.remove(position)
        } else {
            adapter?.selectedItem?.add(position)
        }
        adapter?.notifyItemChanged(position)
        if (adapter?.selectedItem!!.size > 0) {
            supportActionBar!!.title =
                resources.getString(R.string.app_name).plus(" (").plus(adapter?.selectedItem!!.size).plus(")")
        } else {
            setToolbarTitle()
        }
    }
}
