package com.content.blogapplication.auth.data.repository

import android.util.Log
import com.content.blogapplication.auth.data.apiService.AuthApiService
import com.content.blogapplication.auth.data.model.SignInRequest
import com.content.blogapplication.auth.data.model.SignInResponse
import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.util.network.Resource
import com.content.blogapplication.util.network.ResponseHandler
import com.content.blogapplication.util.network.RetrofitInstance
import jakarta.inject.Inject

class AuthRepoRepositoryImpl @Inject constructor(
    private val authApiService : AuthApiService
)  : AuthRepository {

    /*
    private val authApiService by lazy {  RetrofitInstance.getRetrofitInstance().create(
        AuthApiService::class.java)
    }

    private val responseHandler by lazy { ResponseHandler() }

    companion object{
        private var instance : AuthRepository? = null
        fun getInstance() : AuthRepository {
            return instance ?: synchronized(this){
                instance ?: AuthRepoRepositoryImpl().also { instance  = it }
            }
        }
    }

     */

    override suspend fun SignUpUser(signUpRequest: SignUpRequest): SignUpResponse {
        return authApiService.signUpRequest(signUpRequest);
    }

    override suspend fun signInUser(signInRequest: SignInRequest): SignInResponse {
        Log.d("butttonClick","repostory")
        return authApiService.signInRequest(signInRequest);
    }
}