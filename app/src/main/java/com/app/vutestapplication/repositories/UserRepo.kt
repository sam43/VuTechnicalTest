package com.app.vutestapplication.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.vutestapplication.models.Data
import com.app.vutestapplication.models.User
import com.app.vutestapplication.services.BaseRepository
import com.app.vutestapplication.services.ServiceApi

open class UserRepo(private val api : ServiceApi): BaseRepository() {

    open var userListLiveData: MutableList<Data?>? = mutableListOf()

    suspend fun getUsersInfo(pageNo: Int) : MutableList<Data?>? {
        val response = safeApiCall(
            call = { api.getUserListAsync(pageNo).await() },
            errorMessage = "Error Fetching users list"
        )
        userListLiveData = if (response !== null) response?.resData?.toMutableList() else mutableListOf()
        return response?.resData?.toMutableList()
    }
}