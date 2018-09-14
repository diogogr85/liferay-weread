package com.diogo.weread.features.base

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.component_progress_view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.support.closestKodein

abstract class BaseDialogFragment<V: BaseView>: DialogFragment(), KodeinAware, BaseView {

    override val kodein: Kodein by closestKodein()

    protected abstract val layoutId: Int
    protected abstract val presenter: BasePresenter<V>

    protected var hasSavedInstances = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hasSavedInstances = savedInstanceState != null

        setPresenter()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (isAdded) {
            val dialogView = LayoutInflater.from(activity!!.applicationContext)
                    .inflate(layoutId, null)

//        this.edit1 = _view.findViewById(R.id.edit1)
//        this.edit2 = _view.findViewById(R.id.edit2)
//        this.button = _view.findViewById(R.id.somar)
            onCreateDialog(dialogView)

            var alert = AlertDialog.Builder(activity!!)
            alert.setView(dialogView)

            return alert.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }

    protected abstract fun onCreateDialog(layout: View)

    protected abstract fun setPresenter()

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        progressView?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String?) {
        if (message != null && isAdded) {
            Snackbar.make(activity!!.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
        }
    }

}