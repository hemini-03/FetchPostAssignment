package com.feed.apptest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feed.Utility.AppConst.Companion.VIEW_TYPE_ITEM
import com.feed.Utility.AppConst.Companion.VIEW_TYPE_LOADING
import com.feed.Utility.convertDateInputFormatToGiveDateFormat
import com.feed.apptest.R
import com.feed.apptest.model.PostModel

class PostViewDataAdapter
    (private val context:Context,
    private val postList: List<PostModel.HitModel>?,
    private val onItemClick: (Int) -> Unit = {}) :
    BaseRecyclerViewAdapter<RecyclerView.ViewHolder>() {
    var selectedItem:MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_post_list, parent, false))
        } else {
            LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.cell_progressbar,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= itemCount - 1 && isMoreDataExists && !isLoading && loadMoreDataListener != null) {
            isLoading = true
            loadMoreDataListener!!.onLoadMore()
        }
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            postList?.let {
                (holder as ViewHolder).bindData(it[position])
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isMoreDataExists && position >= postList!!.size) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return if (isMoreDataExists) postList!!.size + 1 else postList!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewPostTitle: TextView = itemView.findViewById(R.id.textViewPostTitle)
        private val textViewCreateAt: TextView = itemView.findViewById(R.id.textViewCreateAt)
        private val switchToggle: Switch = itemView.findViewById(R.id.switchToggle)
        private val mainLayout: View = itemView.findViewById(R.id.mainLayout)

        init {
            // Make switch click
            switchToggle.setOnClickListener {
                onItemClick(adapterPosition)
            }
                //row click
            mainLayout.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bindData(hitModel: PostModel.HitModel) {
            // Assign value of view
            hitModel.title?.let {
                textViewPostTitle.text = it
            }
            hitModel.createdAt?.let {
                textViewCreateAt.text =
                    it.convertDateInputFormatToGiveDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "dd/MM/yyyy")
            }

            switchToggle.isChecked = selectedItem.contains(adapterPosition)
        }
    }
}
