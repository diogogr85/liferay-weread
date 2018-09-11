package com.diogo.weread.data.source.local

import android.content.SharedPreferences


inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
    when(T::class) {
        Boolean::class -> return this.getBoolean(key, defaultValue as Boolean) as T
        String::class -> return this.getString(key, defaultValue as String) as T
        Int::class -> return this.getInt(key, defaultValue as Int) as T
        else -> return defaultValue
    }
}

inline fun <reified T> SharedPreferences.put(key: String, value: T) {
    val editor = this.edit()

    when(T::class) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        String::class -> editor.putString(key, value as String)
        Int::class -> editor.putInt(key, value as Int)
    }

    editor.apply()
}