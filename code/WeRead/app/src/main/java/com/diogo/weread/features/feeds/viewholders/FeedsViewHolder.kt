package com.diogo.weread.features.feeds.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.diogo.weread.R

class FeedsViewHolder: RecyclerView.ViewHolder {

    lateinit var titleTextView: TextView
    lateinit var urlTextView: TextView
    lateinit var readTimeTextView: TextView
    lateinit var createdAtTextView: TextView

    private constructor(itemView: View, itemClick: (position: Int) -> Unit) : super(itemView) {
        itemView.findViewById<View>(R.id.itemFeedContainer)
                .setOnClickListener {
                    itemClick(adapterPosition)
                }
    }

    companion object {
        fun newInstance(itemView: View, itemClick: (position: Int) -> Unit): FeedsViewHolder {
            return FeedsViewHolder(itemView, itemClick)
        }
    }

    init {
        with(itemView) {
            titleTextView = findViewById(R.id.itemFeedTitleTextView)
            urlTextView = findViewById(R.id.itemFeedUrlTextView)
            readTimeTextView = findViewById(R.id.itemFeedReadTimeTextView)
            createdAtTextView = findViewById(R.id.itemFeedCreatedAtTextView)
        }
    }



}