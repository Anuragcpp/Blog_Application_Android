package com.content.blogapplication.auth.data.apiService

import com.content.blogapplication.auth.data.model.SignInRequest
import com.content.blogapplication.auth.data.model.SignInResponse
import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.util.network.Resource
import com.content.blogapplication.util.network.Urls
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST(Urls.SIGNUP_URL)
    suspend fun signUpRequest(
        @Body signUpRequest: SignUpRequest
    ) : SignUpResponse

    @POST(Urls.SIGNIN_URL)
    suspend fun signInRequest(
        @Body signInRequest: SignInRequest
    ) : SignInResponse

}