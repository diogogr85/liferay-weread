package com.diogo.weread.features.feeds

import com.diogo.weread.data.models.Feed
import com.diogo.weread.features.base.BaseView


interface FeedsView: BaseView {
    fun onFeedsSuccess(feeds: List<Feed>)
    fun onAddFeedsSuccess(feed: Feed)
    fun onFeedsError()
}