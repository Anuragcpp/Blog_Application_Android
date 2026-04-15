package com.content.blogapplication.util.sharedPreference

import android.content.Context
import androidx.core.content.edit

class SharedPreferenceManager private constructor(context: Context){

    companion  object SharedPreferenceHelper {
        @Volatile
        private var INSTANCE : SharedPreferenceManager? = null

        fun getInstance(context : Context) : SharedPreferenceManager{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: SharedPreferenceManager(context).also {
                    INSTANCE = it
                }
            }
        }
    }

    private val pref = context.applicationContext.getSharedPreferences("app_prefs",Context.MODE_PRIVATE)

    fun setToken(token : String){
        pref.edit { putString("user_token", token) }
    }

    fun getToken() : String? {
        return pref.getString("user_token",null)
    }
}