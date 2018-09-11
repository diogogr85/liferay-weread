package com.diogo.weread.utils

import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wedeploy.android.transport.Response
import org.json.JSONObject


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

inline fun <reified T> String.fromJson(): T {
    return Gson().fromJson(this, T::class.java)
}

inline fun <reified T> Gson.fromJson(json: String): T {
    return this.fromJson<T>(json, object : TypeToken<T>() {}.type)
}

fun Any.toJson(): String {
    return Gson().toJson(this)
}

fun Any.toJsonObject(): JSONObject {
    return JSONObject(this.toJson())
}
