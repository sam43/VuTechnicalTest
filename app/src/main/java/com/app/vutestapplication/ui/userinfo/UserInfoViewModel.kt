package com.app.vutestapplication.ui.userinfo

import androidx.lifecycle.MutableLiveData
import com.app.vutestapplication.models.Data
import com.app.vutestapplication.repositories.UserRepo
import com.app.vutestapplication.services.ApiFactory
import com.app.vutestapplication.utils.BaseViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class UserInfoViewModel(private val repo: UserRepo) : BaseViewModel() {

    open val usersLiveData = MutableLiveData<MutableList<Data?>>()

    fun fetchUsers(pageNo: Int) {
        scope.launch {
            val users = repo.getUsersInfo(pageNo)
            usersLiveData.postValue(users)
        }
    }

    open fun isUserListNull(): Boolean = usersLiveData.value != null

    fun cancelAllRequests() = coroutineContext.cancel()
}
