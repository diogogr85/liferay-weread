package com.diogo.weread.features.feeds

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.diogo.weread.R
import com.diogo.weread.data.models.Feed
import com.diogo.weread.features.base.BaseActivity
import com.diogo.weread.features.feedDetails.FeedDetailsActivity
import com.diogo.weread.features.feeds.adapters.FeedsAdapter
import com.diogo.weread.utils.DialogUtils
import com.diogo.weread.utils.EXTRA_FEEDS_LIST
import com.diogo.weread.utils.EXTRA_FEED_DETAILS
import com.diogo.weread.utils.EXTRA_FEED_DETAILS_TITLE
import kotlinx.android.synthetic.main.activity_feeds.*
import kotlinx.android.synthetic.main.component_empty_view.*
import org.kodein.di.generic.instance
import kotlin.collections.ArrayList


class FeedsActivity: BaseActivity<FeedsView>(), FeedsView {

    override val layoutId: Int = R.layout.activity_feeds

    override val presenter: FeedsPresenter by instance()

    private val feedList = ArrayList<Feed>()

    override fun setPresenter() {
        presenter.bindView(this)
    }

    override fun onCreate() {
        feedsRecyclerView.layoutManager = LinearLayoutManager(this)
        feedsRecyclerView.setHasFixedSize(true)

        feedsRecyclerView.adapter = FeedsAdapter(feedList, applicationContext) {
            if (it.feedItems != null) {
                val intent = Intent(applicationContext, FeedDetailsActivity::class.java)
                intent.putParcelableArrayListExtra(EXTRA_FEED_DETAILS, ArrayList(it.feedItems))
                intent.putExtra(EXTRA_FEED_DETAILS_TITLE, it.title)
                startActivity(intent)
                hasSavedInstances = true
            }
        }

        feedsAddButton.setOnClickListener {
            DialogUtils.show(this) { rssUrl, category ->
                presenter.addFeedRss(rssUrl, category)
            }
        }

        emptyTextView.text = getString(R.string.empty_feed_list_text)
    }

    override fun onResume() {
        super.onResume()
        presenter.getFeeds(!hasSavedInstances)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList(EXTRA_FEEDS_LIST, feedList);
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        feedList.clear()
        feedList.addAll(savedInstanceState?.getParcelableArrayList<Feed>(EXTRA_FEEDS_LIST)!!)
        feedsRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun onFeedsSuccess(feeds: List<Feed>) {
        feedList.clear()
        feedList.addAll(feeds)
        feedsRecyclerView.adapter.notifyDataSetChanged()

        emptyContainerView.visibility = if (feedList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onAddFeedsSuccess(feed: Feed) {
        feedList.add(feed)
        feedsRecyclerView.adapter.notifyDataSetChanged()

        emptyContainerView.visibility = if (feedList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onFeedsError() {
        emptyContainerView.visibility = if (feedList.isEmpty()) View.VISIBLE else View.GONE
    }

}