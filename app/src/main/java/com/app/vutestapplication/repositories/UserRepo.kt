package com.app.vutestapplication.repositories

import android.util.Log
import com.app.vutestapplication.models.Data
import com.app.vutestapplication.services.BaseRepository
import com.app.vutestapplication.services.ServiceApi

class UserRepo(private val api : ServiceApi): BaseRepository() {
    suspend fun getUsersInfo(pageNo: Int) : MutableList<Data?>?{
        try {
            val response = safeApiCall(
                call = { api.getUserListAsync(pageNo).await() },
                errorMessage = "Error Fetching users list"
            )
            return response?.resData?.toMutableList()
        } catch (e: Exception) {
            Log.e("errorH", "msg : ${e.message}")
        }
        return mutableListOf()
    }
}