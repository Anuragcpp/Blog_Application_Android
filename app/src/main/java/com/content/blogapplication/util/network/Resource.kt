package com.content.blogapplication.util.network

data class Resource <out T> ( val status: Status,val data : T?,val message : String?) {
    var hasBeenHandled = false

    fun isResponseHandled() : Boolean {
        return if (hasBeenHandled){
            true
        } else {
            hasBeenHandled = true;
            false
        }
    }

    companion object{

        fun <T> success(data: T?) : Resource<T>{
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error  (msg : String,data : T? ) : Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                null
            )
        }

        fun <T> loading(data : T?) : Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }

    }
}