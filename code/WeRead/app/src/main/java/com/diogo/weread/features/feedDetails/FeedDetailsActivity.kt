package com.diogo.weread.features.feedDetails

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.diogo.weread.R
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.models.FeedItem
import com.diogo.weread.features.base.BaseActivity
import com.diogo.weread.utils.EXTRA_FEEDS_LIST
import com.diogo.weread.utils.EXTRA_FEED_DETAILS
import com.diogo.weread.utils.EXTRA_FEED_DETAILS_TITLE
import kotlinx.android.synthetic.main.activity_feed_details.*
import kotlinx.android.synthetic.main.activity_feeds.*
import org.kodein.di.generic.instance


class FeedDetailsActivity: BaseActivity<FeedDetailsView>(), FeedDetailsView {

    override val layoutId: Int = R.layout.activity_feed_details

    override val presenter: FeedDetailsPresenter by instance()

    private val feedDetailsList = ArrayList<FeedItem>()

    override fun setPresenter() {
        presenter.bindView(this)
    }

    override fun onCreate() {
        title = intent.getStringExtra(EXTRA_FEED_DETAILS_TITLE)

        feedDetailsRecyclerView.layoutManager = LinearLayoutManager(this)
        feedDetailsRecyclerView.setHasFixedSize(true)

        feedDetailsList.clear()
        feedDetailsList.addAll(intent.getParcelableArrayListExtra<FeedItem>(EXTRA_FEED_DETAILS))
        feedDetailsRecyclerView.adapter = FeedDetailsAdapter(feedDetailsList) {
            //TODO - on click feed item
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList(EXTRA_FEED_DETAILS, feedDetailsList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        feedDetailsList.clear()
        feedDetailsList.addAll(savedInstanceState?.getParcelableArrayList<FeedItem>(EXTRA_FEED_DETAILS)!!)
        feedsRecyclerView.adapter.notifyDataSetChanged()
    }

}