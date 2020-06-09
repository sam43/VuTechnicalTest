package com.app.vutestapplication.ui.userinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.vutestapplication.R
import com.app.vutestapplication.models.Data
import com.app.vutestapplication.repositories.UserRepo
import com.app.vutestapplication.services.ApiFactory
import com.app.vutestapplication.testing.TestingActivity
import com.app.vutestapplication.utils.RecyclerAdapterUtil
import com.app.vutestapplication.utils.getViewModel
import com.app.vutestapplication.utils.loadUserAvatar
import kotlinx.android.synthetic.main.user_info_fragment.*
import java.io.Serializable

class UserInfoFragment : Fragment() {

    companion object { // will be needed when new instance of this fragment is required
        fun instance() =
            UserInfoFragment()
    }

    private val repository: UserRepo = UserRepo(ApiFactory.service)

    private val viewModel: UserInfoViewModel by lazy {
        getViewModel<UserInfoViewModel> // without type (UserInfoViewModel) will get compile time error
        {
            UserInfoViewModel(repository)
        }
    }
    /*private val viewModel: UserInfoViewModel by lazy {
        ViewModelProvider(this).get(UserInfoViewModel::class.java)
    }*/
    //private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        viewModel.fetchUsers(1)
    }

    private fun setupViews() {
        btnTestActivity.setOnClickListener { startActivity(Intent(activity, TestingActivity::class.java)) }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelAllRequests()
    }

    private fun setupLayoutManager() {
        val gridLayoutManager = GridLayoutManager(context, 3)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvUsers.layoutManager = gridLayoutManager
        rvUsers.setHasFixedSize(true)
    }

    override fun onStart() {
        super.onStart()
        updateUiWithData()
    }

    private fun updateUiWithData() {
        // TODO:: Update UI with data from VM
        setupLayoutManager()
        viewModel.usersLiveData.observe(
            viewLifecycleOwner,
            Observer {
                if (it.isNotEmpty()) {
                    Log.d("UserList", "data[0]: ${it?.get(0)}")
                    updateList(it)
                }
            }
        )

    }

    private fun updateList(users: MutableList<Data?>?) {
        val viewList = listOf(
            R.id.userImage,
            R.id.tvUserName,
            R.id.tvUserEmail
        )

        users?.let {
            RecyclerAdapterUtil.Builder(requireContext(), it, R.layout.item_grid_view)
                .viewsList(viewList)
                .bindView { _, item, _, innerViews ->
                    val tvUserEmail = innerViews[R.id.tvUserEmail] as TextView
                    val tvUserName = innerViews[R.id.tvUserName] as TextView
                    val userImage = innerViews[R.id.userImage] as ImageView

                    tvUserName.text = item?.resFirstName.plus(" ${item?.resLastName}")
                    tvUserEmail.text = item?.resEmail
                    requireContext().loadUserAvatar(item?.resAvatar, userImage)
                }
                .addClickListener { item, _ ->
                    findNavController().navigate(R.id.action_userInfoFragment_to_detailFragment, bundleOf(
                        "USER_DATA" to item as Serializable
                    ))
                }
                .into(rvUsers)
        }
    }

}
