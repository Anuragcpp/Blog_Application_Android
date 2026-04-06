package com.content.blogapplication.auth.data.apiService

import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.util.network.Resource
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST
    suspend fun signUpRequest(
        @Body signUpRequest: SignUpRequest
    ) : SignUpResponse

}