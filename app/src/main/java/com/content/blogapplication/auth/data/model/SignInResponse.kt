package com.content.blogapplication.auth.data.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("expiresIn")
    val expiresIn: Long
)