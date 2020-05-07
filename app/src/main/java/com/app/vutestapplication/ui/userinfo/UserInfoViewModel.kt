package com.app.vutestapplication.ui.userinfo

import androidx.lifecycle.MutableLiveData
import com.app.vutestapplication.models.Data
import com.app.vutestapplication.repositories.UserRepo
import com.app.vutestapplication.services.ApiFactory
import com.app.vutestapplication.utils.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class UserInfoViewModel : BaseViewModel() {

    /**
     * We need to calculate and check business logic here in viewmodel
     * and set it as a observable value to be available for the UI end
     * */

    private val repository: UserRepo = UserRepo(ApiFactory.service)
    val usersLiveData = MutableLiveData<MutableList<Data?>>()

    fun fetchUsers(pageNo: Int) {
        scope.launch {
            val users = async { repository.getUsersInfo(pageNo) }
            usersLiveData.postValue(users.await())
            /**
             * We can asynchronously call another api endpoint if need
             * we get the firstOne then pass that awaited value to the secondApi method
             * We can store it as separated results or the as a combined result in
             * to the viewmodel to use it further
             * */
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
