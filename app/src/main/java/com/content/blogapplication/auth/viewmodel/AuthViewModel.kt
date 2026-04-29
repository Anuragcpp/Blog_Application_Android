package com.content.blogapplication.auth.viewmodel

import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.content.blogapplication.application.BaseApplication
import com.content.blogapplication.auth.data.model.SignInRequest
import com.content.blogapplication.auth.data.model.SignInResponse
import com.content.blogapplication.auth.data.model.SignUpRequest
import com.content.blogapplication.auth.data.model.SignUpResponse
import com.content.blogapplication.auth.data.repository.AuthRepoRepositoryImpl
import com.content.blogapplication.auth.data.repository.AuthRepository
import com.content.blogapplication.util.network.ApiStateResource
import com.content.blogapplication.util.network.Resource
import com.content.blogapplication.util.sharedPreference.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val authRepository : AuthRepository,
    private val preferenceManager : SharedPreferenceManager
) : ViewModel() {
    /*
    private val authRepository by lazy {
        AuthRepoRepositoryImpl.getInstance()
    }

     */

    private val _signUpUserState = MutableStateFlow<ApiStateResource<SignUpResponse>>(ApiStateResource.Idle)
    val signUpUserState = _signUpUserState.asStateFlow();

    private val _signInUserState = MutableStateFlow<ApiStateResource<SignInResponse>>(
        ApiStateResource.Idle)
    val signInUserState = _signInUserState.asStateFlow();

//    private val preferenceManager : SharedPreferenceManager by lazy { SharedPreferenceManager.getInstance(
//        BaseApplication.mContext) }

    fun signUpUser(
        name : String,
        email : String,
        password : String
    ) {
        viewModelScope.launch (Dispatchers.IO){
            _signUpUserState.value = ApiStateResource.Loading
            try {
                val response = authRepository.SignUpUser(SignUpRequest(name,email,password))
                preferenceManager.setToken(response.data!!)

                _signUpUserState.value = ApiStateResource.Success<SignUpResponse>(response);
            }catch (e : retrofit2.HttpException){

                _signUpUserState.value = ApiStateResource.Error(
                    e.response()?.errorBody()?.toString() ?: "Internal Server Error"
                )
            } catch (e : IOException){
                Log.d("butttonClick", "IOException ${e.message}")

            }catch (e : Exception){

                _signUpUserState.value = ApiStateResource.Error( "Something Went wrong" )
            }
        }
    }

    fun signInUser(
        email : String,
        password: String
    ) {
        Log.d("butttonClick","viewmod before")
        viewModelScope.launch (Dispatchers.IO) {
            Log.d("butttonClick","viewmodl")
            _signInUserState.value = ApiStateResource.Loading
            try {
                val response = authRepository.signInUser(SignInRequest(email,password))
                preferenceManager.setToken(token = response.token);
                _signInUserState.value = ApiStateResource.Success(response)
                Log.d("butttonClick","Response received : $response")
            }catch ( e : retrofit2.HttpException ){
                _signInUserState.value = ApiStateResource.Error( e.message ?: "Something Went wrong" )
                Log.d("butttonClick", "http retrofit2.HttpException ${e.message}")
            }catch ( e : IOException){
                _signInUserState.value = ApiStateResource.Error( e.message ?: "No Internet Connection ")
                _signInUserState.value = ApiStateResource.Error( "No Internet Connection" )
            }catch ( e : Exception ){
                _signInUserState.value = ApiStateResource.Error( e.message ?: "Something Went wrong" )
                Log.d("butttonClick", "Exception ${e.message}")
            }
        }
    }


}