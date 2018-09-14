package com.diogo.weread.features.feedDetails

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.diogo.weread.R
import com.diogo.weread.data.models.Feed
import com.diogo.weread.data.models.FeedItem
import com.diogo.weread.features.base.BaseActivity
import com.diogo.weread.features.news.NewsActivity
import com.diogo.weread.utils.*
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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        feedDetailsRecyclerView.layoutManager = LinearLayoutManager(this)
        feedDetailsRecyclerView.setHasFixedSize(true)

        feedDetailsList.clear()
        feedDetailsList.addAll(intent.getParcelableArrayListExtra<FeedItem>(EXTRA_FEED_DETAILS))
        feedDetailsRecyclerView.adapter = FeedDetailsAdapter(feedDetailsList) { feedItem ->
            val intent = Intent(applicationContext, NewsActivity::class.java)
            intent.putExtra(EXTRA_FEED_NEWS_TITLE, feedItem.title)
            intent.putExtra(EXTRA_FEED_NEWS_URL, feedItem.url)
            startActivity(intent)
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