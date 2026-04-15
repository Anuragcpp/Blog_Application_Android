package com.content.blogapplication.auth.data.repository

import com.content.blogapplication.auth.data.model.SignInRequest
import com.content.blogapplication.auth.data.model.SignInResponse
import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.util.network.Resource

interface AuthRepository {

    suspend fun SignUpUser (signUpRequest: SignUpRequest) : SignUpResponse

    suspend fun signInUser( signInRequest: SignInRequest) : SignInResponse

}