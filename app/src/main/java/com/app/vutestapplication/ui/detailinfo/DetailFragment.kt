package com.app.vutestapplication.ui.detailinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.vutestapplication.R
import com.app.vutestapplication.models.Data
import com.app.vutestapplication.utils.getViewModel
import com.app.vutestapplication.utils.loadUserAvatar
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by lazy { getViewModel<DetailViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getArgumentsFromList()
    }

    private fun getArgumentsFromList() {
        arguments.let {
            updateUI(it?.getSerializable("USER_DATA") as Data)
        }
    }

    private fun updateUI(user: Data?) {
        requireContext().loadUserAvatar(
            user?.resAvatar,
            ivThumbPic
        )
        tvUserNameDetails.text = user?.resFirstName.plus(" ${user?.resLastName}")
    }

}
