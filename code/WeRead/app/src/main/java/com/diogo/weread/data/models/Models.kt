package com.diogo.weread.data.models


data class Session(
        val email: String,
        val password: String
)

data class User(
        val id: String,
        val name: String,
        val email: String
)