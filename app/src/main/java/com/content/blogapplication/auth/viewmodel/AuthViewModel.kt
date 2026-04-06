package com.content.blogapplication.auth.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.auth.data.repository.AuthRepoRepositoryImpl
import com.content.blogapplication.util.network.Resource
import com.content.blogapplication.util.network.Status
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val authRepository by lazy {
        AuthRepoRepositoryImpl.getInstance()
    }

    val signUpLiveData = MediatorLiveData<Resource<SignUpResponse>> ()

    fun signUpUser(
        name : String,
        email : String,
        password : String
    ) {
        signUpLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            signUpLiveData.postValue(authRepository.SignUpUser(SignUpRequest(name,email,password)))
        }
    }
}