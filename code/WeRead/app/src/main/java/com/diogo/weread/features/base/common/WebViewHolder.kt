package com.diogo.weread.features.base.common

import android.graphics.Bitmap
import android.net.http.SslError
import android.util.Log
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient


class WebViewHolder private constructor(val itemView: WebView, val url: String,
                                        val onLoadPage: (Boolean) -> Unit) {

    companion object {
        fun newInstance(itemView: WebView, url: String, onLoadPage: (Boolean) -> Unit): WebViewHolder {
            return WebViewHolder(itemView, url, onLoadPage)
        }
    }

    init {
        setupWebView(url)
    }

    private fun setupWebView(url: String) {
        Log.d("WEBVIEW-URL", url)
        itemView.settings.javaScriptEnabled = true
        itemView.settings.useWideViewPort = true

        itemView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                onLoadPage(true)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                onLoadPage(false)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view?.loadUrl(url);
                }
                return true;
            }
        }

        itemView.loadUrl(url)
    }

    fun onGoBackPage(): Boolean {
        val canGoBack = itemView.canGoBack()
        if (canGoBack) {
            itemView.goBack()
        }

        return !canGoBack
    }

}
