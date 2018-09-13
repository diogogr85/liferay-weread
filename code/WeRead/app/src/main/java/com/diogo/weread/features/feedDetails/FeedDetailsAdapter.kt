package com.diogo.weread.features.feedDetails

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diogo.weread.R
import com.diogo.weread.data.models.FeedItem
import com.diogo.weread.features.feeds.viewholders.FeedsViewHolder


class FeedDetailsAdapter(private val feedDetailsList: ArrayList<FeedItem>,
                         private val onFeedDetailsClick: (item: FeedItem) -> Unit): RecyclerView.Adapter<FeedsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed_details, parent, false)

        return FeedsViewHolder.newInstance(itemView) { position ->
            onFeedDetailsClick(feedDetailsList[position])
        }
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        if (feedDetailsList != null) {
            with(feedDetailsList[position]) {
                holder.titleTextView.text = title
                holder.urlTextView.text = url
                holder.descriptionTextView.text = description
            }
        }
    }

    override fun getItemCount() = feedDetailsList.size
}
