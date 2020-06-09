package com.app.vutestapplication.ui.userinfo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.app.vutestapplication.models.Data
import com.app.vutestapplication.repositories.UserRepo
import com.app.vutestapplication.services.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.runners.MockitoJUnitRunner

class UserInfoViewModelTest {

    @Mock
    private val repository: UserRepo = UserRepo(ApiFactory.service)

    @Mock
    private lateinit var observer: Observer<MutableList<Data?>?>

    @Spy
    private val userListLiveData: MutableList<Data?> = mutableListOf()

    private lateinit var viewModel: UserInfoViewModel

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val users = listOf(
        Data("https://imgur.com/t/funny/WdyzYac", "lakers@hum.com", "Lakers", 10),
        Data("https://imgur.com/t/funny/WdyzYac", "celtics@spa.com","Celtics", 11),
        Data("https://imgur.com/t/funny/WdyzYac", "abc@hotmail.com","Abc", 12),
        Data("https://imgur.com/t/funny/WdyzYac", "barca@yahoo.com","Barca", 13)
    )

    @Before
    fun setUp() {
        //`when`(repository.userListLiveData).thenReturn(userListLiveData)
        viewModel = UserInfoViewModel(repository)
        viewModel.usersLiveData.observeForever(observer)
    }

    @Test
    fun testNull() {
        //`when`(repository.userListLiveData == null).thenReturn(null)
        Assert.assertNotNull(viewModel.usersLiveData)
        Assert.assertTrue(viewModel.usersLiveData.hasObservers())
    }


    @Test
    fun fetchUsersTest() {
        viewModel.usersLiveData
        GlobalScope.launch(Dispatchers.IO) {
            verify(repository)?.getUsersInfo(1)
        }
    }

    @Test
    fun observerTest() {
        viewModel.usersLiveData
        GlobalScope.launch(Dispatchers.IO) {
            verify(observer)?.onChanged(userListLiveData)
        }
    }
}