package com.example.news.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object{

        fun <T> success(data : T?) = Resource<T>(Status.SUCCESS,data,null)
        fun <T> error(data : T?,msg : String?) = Resource<T>(Status.ERROR,data,msg)
        fun <T> loading(data : T?) = Resource<T>(Status.LOADING,data,null)




    }

}