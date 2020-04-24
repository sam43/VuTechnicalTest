package com.app.vutestapplication.ui.userinfo

import androidx.lifecycle.MutableLiveData
import com.app.vutestapplication.models.Data
import com.app.vutestapplication.repositories.UserRepo
import com.app.vutestapplication.services.ApiFactory
import com.app.vutestapplication.utils.BaseViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class UserInfoViewModel : BaseViewModel() {

    private val repository: UserRepo = UserRepo(ApiFactory.service)
    val usersLiveData = MutableLiveData<MutableList<Data?>>()

    fun fetchUsers(pageNo: Int) {
        scope.launch {
            val users = repository.getUsersInfo(pageNo)
            usersLiveData.postValue(users)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
