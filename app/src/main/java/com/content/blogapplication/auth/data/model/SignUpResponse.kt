package com.content.blogapplication.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val status : Int,
    val message : String,
    val data : String?
)
