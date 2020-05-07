package com.app.vutestapplication.services.not_in_use

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object APIHelper {
    const val DEFAULT_RETRIES = 3
    fun <T> enqueueWithRetry(
        call: Call<T>,
        retryCount: Int,
        callback: Callback<T?>
    ) {
        call.enqueue(object : RetryableCallback<T>(call, retryCount) {

            /*override fun onFinalResponse(
                call: Call<T?>?,
                response: Response<T?>?
            ) {
                callback.onResponse(call, response)
            }

            override fun onFinalFailure(
                call: Call<T?>?,
                t: Throwable?
            ) {
                callback.onFailure(call, t)
            }*/
        })
    }

    fun <T> enqueueWithRetry(
        call: Call<T>,
        callback: Callback<T?>
    ) {
        enqueueWithRetry(
            call,
            DEFAULT_RETRIES,
            callback
        )
    }

    fun isCallSuccess(response: Response<*>): Boolean {
        val code = response.code()
        return code >= 200 && code < 400
    }
}