package com.app.vutestapplication.services

import com.app.vutestapplication.models.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("users")
    fun getUserListAsync(@Query("page") pageNo: Int): Deferred<Response<User>>
}