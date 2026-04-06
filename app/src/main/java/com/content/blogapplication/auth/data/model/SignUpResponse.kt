package com.content.blogapplication.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val status : String,
    val message : String,
    val data : String?
)
