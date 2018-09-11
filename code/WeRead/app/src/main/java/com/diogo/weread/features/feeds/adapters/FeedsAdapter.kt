package com.diogo.weread.features.feeds.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diogo.weread.R
import com.diogo.weread.data.loaders.loadImage
import com.diogo.weread.data.models.Feed
import com.diogo.weread.features.feeds.viewholders.FeedsViewHolder


class FeedsAdapter(private val feedList: ArrayList<Feed>,
                   private val context: Context,
                   private val onFeedClicked: (item: Feed) -> Unit): RecyclerView.Adapter<FeedsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed, parent, false)

        return FeedsViewHolder.newInstance(itemView) { position ->
            onFeedClicked(feedList[position])
        }
    }

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        if (feedList != null) {
            with(feedList[position]) {
                holder.titleTextView.text = title
                holder.urlTextView.text = url
                holder.descriptionTextView.text = description
                holder.iconImageView.loadImage(context, feedImageUrl)
            }
        }
    }

    override fun getItemCount() = feedList.size
}
