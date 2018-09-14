package com.diogo.weread.data.source.local

import android.content.SharedPreferences
import com.diogo.weread.data.models.Session
import com.diogo.weread.data.models.User
import com.diogo.weread.utils.*


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

class SharedPrefsManager(private val prefs: SharedPreferences) {

    fun cacheUser(user: User) {
        prefs.put(PREFS_WEREAD_CURRENT_USER, user.toJson())
    }

    fun cacheSession(session: Session) {
        prefs.put(PREFS_WEREAD_SESSION, session.toJson())
        prefs.put(PREFS_WEREAD_ACCESS_TOKEN, session.accessToken)
    }

    fun getCachedUser(): User? {
        return prefs.getString(PREFS_WEREAD_CURRENT_USER, "").fromJson<User>()
    }

    fun getAccessToken(): String {
        return prefs.getString(PREFS_WEREAD_ACCESS_TOKEN, "")
    }

}
