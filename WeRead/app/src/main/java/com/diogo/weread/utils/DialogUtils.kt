package com.diogo.weread.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.diogo.weread.R
import kotlinx.android.synthetic.main.dialog_add_feed.view.*

class DialogUtils {

    companion object {
        fun show(ctx: Context,
                 onPositiveClickListener: (rssUrl: String, category: String) -> Unit) {
            val dialogView = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_feed, null)
            val builder = AlertDialog.Builder(ctx)
                    .setView(dialogView)
                    .setTitle("Add feed")

            val  alertDialog = builder.show()

            val adapter = ArrayAdapter<String>(ctx,
                    android.R.layout.simple_spinner_item,
                    ctx.resources.getStringArray(R.array.category_arrays))
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            dialogView.dialogCategorySpinner.adapter = adapter

            //Positive button click listener
            dialogView.dialogAddButton.setOnClickListener {
                val feedUrl = dialogView.dialogUrlEditText.text.toString().trim()
                val category: String = adapter.getItem(dialogView.dialogCategorySpinner.selectedItemPosition)

                if (!feedUrl.isEmpty() && feedUrl.contains(".xml")) {
                    onPositiveClickListener(feedUrl, category)
                    alertDialog.dismiss()
                } else {
                    dialogView.dialogUrlEditText.error = "Invalid url"
                }
            }

            //Negative button click listener
            dialogView.dialogCancelButton.setOnClickListener {
                //dismiss dialog
                alertDialog.dismiss()
            }
        }
    }

}