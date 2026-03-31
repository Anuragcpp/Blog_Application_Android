package com.content.blogapplication.auth.data.repository

import com.content.blogapplication.auth.data.apiService.AuthApiService
import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.util.network.Resource
import com.content.blogapplication.util.network.ResponseHandler
import com.content.blogapplication.util.network.RetrofitInstance

class AuthRepoRepositoryImpl  : AuthRepository {

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

    override suspend fun SignUpUser(signUpRequest: SignUpRequest): Resource<SignUpResponse> {
       return try {
           val response = authApiService.signUpRequest(signUpRequest)
           responseHandler.handlerSuccess(response)
       }catch (e : Exception) {
           responseHandler.handleException(e)
       }

    }
}