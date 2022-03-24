package com.appfiz.stockie.data.response

/**
 * A generic class that holds a value or an exception
 */
sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val errorMsg: String) : Response<Nothing>()
}