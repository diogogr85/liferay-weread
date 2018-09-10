package com.diogo.weread.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Auth(
        val email: String,
        val password: String
)

data class Session(
        @SerializedName("access_token")
        val accessToken: String,
        @SerializedName("refresh_token")
        val refreshToken: String,
        val scope: String,
        @SerializedName("expires_in")
        val expiresIn: String,
        @SerializedName("token_type")
        val tokenType: String

)

data class User(
        val id: String? = null,
        val name: String,
        val email: String
)

@Entity(tableName = "feeds")
data class Feed(
        @PrimaryKey
        val id: String,
        val userId: String,
        val title: String,
        val url: String,
        val readTime: Int,
        val createdAt: String
)
