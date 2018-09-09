package com.diogo.weread.utils

import android.widget.EditText
import com.google.gson.Gson
import com.wedeploy.android.transport.Response


fun EditText.validateField(errorMsg: String): Boolean {
    if (!this.text.isEmpty()) {
        return true
    }

    this.error = errorMsg
    return false
}

inline fun <reified T> Response.getBody(): T {
    return Gson().fromJson(body, T::class.java)
}