package com.example.dongnaefriend_android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dongnaefriend_android.databinding.FragmentAccountshareBinding
import model.AccountsharePost

class AccountshareDetailFragment : Fragment() {
    private var post: AccountsharePost? = null
    private lateinit var binding: FragmentAccountshareBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getSerializable("post_detail")?.let {
            post = it as? AccountsharePost

        }
    }
}
