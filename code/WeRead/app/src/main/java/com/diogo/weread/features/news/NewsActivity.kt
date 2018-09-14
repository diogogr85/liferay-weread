package com.diogo.weread.features.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.diogo.weread.R
import com.diogo.weread.features.base.common.WebViewHolder
import com.diogo.weread.utils.EXTRA_FEED_NEWS_TITLE
import com.diogo.weread.utils.EXTRA_FEED_NEWS_URL
import kotlinx.android.synthetic.main.component_progress_view.*


class NewsActivity: AppCompatActivity() {

    private lateinit var webViewHolder: WebViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(EXTRA_FEED_NEWS_TITLE)

        val url: String
        if (intent.hasExtra(EXTRA_FEED_NEWS_URL)) {
            url = intent.getStringExtra(EXTRA_FEED_NEWS_URL)
        } else {
            url = "http://www.liferay.com.br"
        }
        webViewHolder = WebViewHolder.newInstance(findViewById(R.id.webView), url, this::showProgress)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (webViewHolder.onGoBackPage()) {
            super.onBackPressed()
        }
    }

    private fun showProgress(show: Boolean) {
        progressView?.visibility = if (show) View.VISIBLE else View.GONE
    }

}