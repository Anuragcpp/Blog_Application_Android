package com.content.blogapplication.mainactivty.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import com.content.blogapplication.application.BaseApplication
import com.content.blogapplication.mainactivty.state.AuthState
import com.content.blogapplication.util.sharedPreference.SharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {
    private val _authState : MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Loading)
    val authState : StateFlow<AuthState> get() = _authState

//    private val sharedPreferenceManager : SharedPreferenceManager by lazy {
//        SharedPreferenceManager.getInstance(BaseApplication.mContext) }

    fun checkAuthState(){
        val token = sharedPreferenceManager.getToken()
        Log.d("tokenCheck","token isEmptyorNull ${token.isNullOrBlank()}")
        if (token.isNullOrBlank()) _authState.value = AuthState.Unauthenticated
        else _authState.value = AuthState.Authenticated
    }

}