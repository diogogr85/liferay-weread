package com.diogo.weread.features.feeds

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.diogo.weread.R
import com.diogo.weread.data.models.Feed
import com.diogo.weread.features.base.BaseActivity
import com.diogo.weread.features.feeds.adapters.FeedsAdapter
import kotlinx.android.synthetic.main.activity_feeds.*
import kotlinx.android.synthetic.main.component_empty_view.*
import org.kodein.di.generic.instance


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

        feedsRecyclerView.adapter = FeedsAdapter(feedList) {
            //TODO - on click feed item
        }

        feedsAddButton.setOnClickListener {
            //TODO - add feed
        }

        emptyTextView.text = getString(R.string.empty_feed_list_text)
    }

    override fun onResume() {
        super.onResume()
        presenter.getFeeds()

    }

    override fun onFeedsSuccess(feeds: Array<Feed>) {
        feedList.clear()
        feedList.addAll(feeds)
        feedsRecyclerView.adapter.notifyDataSetChanged()

        emptyContainerView.visibility = if (feedList.isEmpty()) View.VISIBLE else View.GONE
    }



}