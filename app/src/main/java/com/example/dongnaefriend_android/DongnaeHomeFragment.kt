package com.example.dongnaefriend_android

import DongnaeDetailFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.dongnaefriend_android.databinding.FragmentDongnaeHomeBinding
import model.Post


private val dongnaeshareData = mutableListOf<Post>()

class DongnaeHomeFragment : Fragment() {

    private var _binding: FragmentDongnaeHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDongnaeHomeBinding.inflate(inflater)



        binding.restaurant1.setOnClickListener(){

            var post = Post(1000,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.restaurant2.setOnClickListener(){

            var post = Post(1001,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.facility1.setOnClickListener(){

            var post = Post(1002,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.facility2.setOnClickListener(){

            var post = Post(1003,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.informationshare1.setOnClickListener(){

            var post = Post(1004,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.informationshare2.setOnClickListener(){

            var post = Post(1005,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.together1.setOnClickListener(){

            var post = Post(1006,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.together2.setOnClickListener(){

            var post = Post(1007,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.pass1.setOnClickListener(){

            var post = Post(1008,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.pass2.setOnClickListener(){

            var post = Post(1009,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.etc1.setOnClickListener(){

            var post = Post(1010,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.etc2.setOnClickListener(){

            var post = Post(1011,0,"","","","","","","")

            (activity as DongnaeInformationActivity).goneForDetail()

            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }







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