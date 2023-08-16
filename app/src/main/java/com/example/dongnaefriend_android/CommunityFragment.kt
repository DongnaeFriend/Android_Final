package com.example.dongnaefriend_android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class CommunityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false)
    }
    //override fun onStart() {
      //  super.onStart()
        //val intent = Intent(getActivity(), DongnaeInformationActivity::class.java)
        //startActivity(intent)
    //}


}