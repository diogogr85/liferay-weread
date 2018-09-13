package com.diogo.weread.features.feedDetails

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.diogo.weread.R

class FeedDetailsViewHolder private constructor(itemView: View, itemClick: (position: Int) -> Unit)
    : RecyclerView.ViewHolder(itemView) {

    lateinit var titleTextView: TextView
    lateinit var urlTextView: TextView
    lateinit var descriptionTextView: TextView

    init {
        itemView.findViewById<View>(R.id.itemFeedContainer)
                .setOnClickListener {
                    itemClick(adapterPosition)
                }
    }

    companion object {
        fun newInstance(itemView: View, itemClick: (position: Int) -> Unit): FeedDetailsViewHolder {
            return FeedDetailsViewHolder(itemView, itemClick)
        }
    }

    init {
        with(itemView) {
            titleTextView = findViewById(R.id.itemFeedTitleTextView)
            urlTextView = findViewById(R.id.itemFeedUrlTextView)
            descriptionTextView = findViewById(R.id.itemFeedDescriptionTextView)
        }
    }



}