package com.app.vutestapplication.services

import com.app.vutestapplication.models.User
import com.app.vutestapplication.services.with_annotations.Retry
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @Retry
    @GET("users")
    fun getUserListAsync(@Query("page") pageNo: Int): Deferred<Response<User>>
}