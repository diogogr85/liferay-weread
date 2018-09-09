package com.diogo.weread.data.models

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
