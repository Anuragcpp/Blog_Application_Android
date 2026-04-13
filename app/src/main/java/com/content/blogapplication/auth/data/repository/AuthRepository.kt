package com.content.blogapplication.auth.data.repository

import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.util.network.Resource

interface AuthRepository {

    suspend fun SignUpUser (signUpRequest: SignUpRequest) : SignUpResponse

}