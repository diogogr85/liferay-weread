package com.diogo.weread.utils

import android.widget.EditText


fun EditText.validateField(errorMsg: String): Boolean {
    if (!this.text.isEmpty()) {
        return true
    }

    this.error = errorMsg
    return false
}