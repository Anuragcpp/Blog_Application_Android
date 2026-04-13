package com.content.blogapplication.auth.viewmodel

import android.net.http.HttpException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.auth.data.repository.AuthRepoRepositoryImpl
import com.content.blogapplication.util.network.ApiStateResource
import com.content.blogapplication.util.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException

class AuthViewModel : ViewModel() {
    private val authRepository by lazy {
        AuthRepoRepositoryImpl.getInstance()
    }

    private val _signUpUserState = MutableStateFlow<ApiStateResource<SignUpResponse>>(ApiStateResource.Idle)
    val signUpUserState = _signUpUserState.asStateFlow();

    fun signUpUser(
        name : String,
        email : String,
        password : String
    ) {
        viewModelScope.launch (Dispatchers.IO){
            _signUpUserState.value = ApiStateResource.Loading
            try {
                val response = authRepository.SignUpUser(SignUpRequest(name,email,password))
                _signUpUserState.value = ApiStateResource.Success<SignUpResponse>(response);
            }catch (e : retrofit2.HttpException){
                _signUpUserState.value = ApiStateResource.Error(
                    e.response()?.errorBody()?.string() ?: "Internal Server Error"
                )
            } catch (e : IOException){
                _signUpUserState.value = ApiStateResource.Error( "No Internet Connection" )
            }catch (e : Exception){
                _signUpUserState.value = ApiStateResource.Error( "Something Went wrong" )
            }
        }
    }
}