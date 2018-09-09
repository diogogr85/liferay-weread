package com.diogo.weread.features.feeds.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diogo.weread.R
import com.diogo.weread.data.models.Feed
import com.diogo.weread.features.feeds.viewholders.FeedsViewHolder

class FeedsAdapter(private val feedList: ArrayList<Feed>,
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
                holder.readTimeTextView.text = readTime.toString()  //TODO - time parser
                holder.createdAtTextView.text = createdAt   //TODO - date parser
            }
        }
    }

    override fun getItemCount() = feedList.size
}
