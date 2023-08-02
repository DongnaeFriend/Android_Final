package com.example.dongnaefriend_android

import android.nfc.Tag
import android.os.Bundle
import android.provider.Telephony
import android.provider.Telephony.Mms.Rate
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaefriend_android.adapter.AccountshareAdapter
import com.example.dongnaefriend_android.databinding.FragmentAccountshareBinding
import model.Post

class AccountshareFragment : Fragment() {
    lateinit var binding: FragmentAccountshareBinding
    private lateinit var accountshareAdapter: AccountshareAdapter
    private val accountshareData = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountshareBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAccountshareList()
        initAccountshareRecyclerView()

        binding.btnAccountshare.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.container_fragment,AccountshareWriteFragment()).commit()
            (activity as AccountbookActivity).containerFragmentGone()
        }
    }

    private fun initAccountshareRecyclerView() {
        binding.rvAccountshare.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        accountshareAdapter = AccountshareAdapter()
        accountshareAdapter.dataList = accountshareData
        binding.rvAccountshare.adapter = accountshareAdapter
    }

    private fun initAccountshareList() {
        accountshareData.addAll(
            listOf<Post>(
                Post(
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "xx동","5","3"
                 ),
                Post(
                    R.drawable.image_myaccount,
                    "학교근처맛집", "1", "3","학교 근처에 먹을게 없어요 배고파요",
                    "00동","5","3"
                ),
                Post(
                    R.drawable.image_myaccount,
                    "출근중인데", "1", "3","집에가고싶다...",
                    "ㅁㅁ동","5","3"
                ),
                Post(
                    R.drawable.image_myaccount,
                    "오늘 저희 강아지가", "1", "3","휴지를 다 물어뜯어놨네요. 어떻게이런일이",
                    "ㅎㅎ동","5","3"
                ),
                Post(
                    R.drawable.image_myaccount,
                    "구내식당", "1", "3","노맛",
                    "ㅇㅇ동","5","3"
                ),
                Post(
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㄹㄹ동","5","3"
                ),
                Post(
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㅂㅂ동","5","3"
                ),
                Post(
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㅎㅎ동","5","3"
                )

            )
        )
    }


}