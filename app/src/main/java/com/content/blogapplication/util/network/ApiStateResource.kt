package com.content.blogapplication.util.network

import com.content.blogapplication.auth.data.model.SignUpResponse

sealed class ApiStateResource<out T> {
    object Idle : ApiStateResource<Nothing>()
    object Loading : ApiStateResource<Nothing>()
    data class Success <out T>(val data : T) : ApiStateResource<T>()
    data class Error (val message : String) : ApiStateResource<Nothing>()
}