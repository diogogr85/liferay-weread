package com.diogo.weread.features.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.component_progress_view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


abstract class BaseActivity<V: BaseView>: AppCompatActivity(), KodeinAware, BaseView {

    override val kodein: Kodein by closestKodein()

    protected abstract val layoutId: Int
    protected abstract val presenter: BasePresenter<V>

    protected var hasSavedInstances = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        hasSavedInstances = savedInstanceState != null

        setPresenter()
        onCreate()
    }

    protected abstract fun setPresenter()

    protected abstract fun onCreate()

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }


    override fun showProgress(show: Boolean) {
        progressView?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
        presenter.unsubscribe()
    }

}