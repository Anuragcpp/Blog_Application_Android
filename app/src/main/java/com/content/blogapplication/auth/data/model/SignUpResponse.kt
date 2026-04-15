package com.content.blogapplication.auth.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: String?
)
