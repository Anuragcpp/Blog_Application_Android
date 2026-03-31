package com.content.blogapplication.util.network

import android.net.http.HttpException
import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONObject
import java.net.SocketTimeoutException

open class ResponseHandler {

    fun <T: Any> handlerSuccess(data : T) : Resource<T> {
        return Resource.success(data)
    }

    fun <T: Any> handleException(e : Exception) : Resource<T> {
        Log.e("TAG", "handleException: " + e.stackTrace.toString())
        e.printStackTrace()
        Log.e("TAG", "handleException: " + e.cause.toString())
        Log.e("TAG", "handleException: " + e.message.toString())

        return when(e) {
            is retrofit2.HttpException -> {

                val errorMessage = if(e.code()!=400 && e.code()!=403){
                    getErrorMessage(e.code(),e)

                } else{
                    val body=e.response()?.errorBody()
                    getErrorJSON(body)
                }


                //Sentry.captureMessage("error Message$errorMessage")
                Resource.error(errorMessage, null)
            }
            is SocketTimeoutException -> Resource.error(getErrorMessage(501, SocketTimeoutException()), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE, e), null)
        }
    }

    private fun getErrorMessage(code: Int, e: Exception): String {
        return when (code) {
            // SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            403 -> "Authentication failed"
            404 -> "Not found"
            400 -> e.message.toString()
            429 -> "Try after sometime"
            500 -> "Internal Server Error"
            501 -> "Slow internet connection, please try again."
            else -> "Something went wrong"
        }
    }

    private fun getErrorJSON(body: ResponseBody?): String {
        return try{
            val mainObject = JSONObject(body!!.string())

            mainObject.getString("message")

        }
        catch (e:Exception){
            "Something wrong happened"
        }

    }

}