package com.dbnaza.desafioenjoei.api

interface ResultCallback<T> {
    fun onSuccess(result: T)
    fun onError(t: Throwable)
}