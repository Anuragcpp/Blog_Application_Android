package com.content.blogapplication.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    @SerialName("name")
    var name :  String? = null,

    @SerialName("email")
    var email : String? = null,

    @SerialName("password")
    var password : String? = null


)