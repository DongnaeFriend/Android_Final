package com.example.dongnaefriend_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.dongnaefriend_android.databinding.FragmentDongnaeHomeBinding

class DongnaeHomeFragment : Fragment() {

    private var _binding: FragmentDongnaeHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDongnaeHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DongnaeInformationActivity의 imageWrite ImageView
//        val imageWrite: ImageView? = activity?.findViewById(R.id.image_write)
//        imageWrite?.setOnClickListener {
//
//            parentFragmentManager.commit {
//                //replace(R.id.container_dongnae, DongnaeWriteFragment())
//                addToBackStack(null)
//            }
        }
    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}