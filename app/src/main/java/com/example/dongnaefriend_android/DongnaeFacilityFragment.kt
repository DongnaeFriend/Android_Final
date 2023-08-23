package com.example.dongnaefriend_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaefriend_android.adapter.DongnaeshareAdapter
import com.example.dongnaefriend_android.databinding.FragmentDongnaeshareBinding
import model.Post

class DongnaeFacilityFragment : Fragment() {
    lateinit var binding: FragmentDongnaeshareBinding
    private lateinit var DongnaeshareAdapter: DongnaeshareAdapter
    private val dongnaeshareData = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDongnaeshareBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRestaurantList()
        initRestaurantRecyclerView()
    }

    private fun initRestaurantRecyclerView() {
        binding.rvDongnaeshare.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        DongnaeshareAdapter = DongnaeshareAdapter()
        DongnaeshareAdapter.dataList = dongnaeshareData

        // 아이템 클릭 리스너 설정
        DongnaeshareAdapter.onItemClickListener = object : DongnaeshareAdapter.OnItemClickListener {
            override fun onItemClick(post: Post) {
                val fragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post)) // container_main은 교체하고자 하는 프래그먼트의 ID입니다.
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }

        binding.rvDongnaeshare.adapter = DongnaeshareAdapter
    }

    private fun initRestaurantList() {
        dongnaeshareData.addAll(
            listOf<Post>(
                Post(
                    210,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "xx동","5","3"
                ),
                Post(
                    211,
                    R.drawable.image_myaccount,
                    "학교근처맛집", "1", "3","학교 근처에 먹을게 없어요 배고파요",
                    "00동","5","3"
                ),
                Post(
                    212,
                    R.drawable.image_myaccount,
                    "출근중인데", "1", "3","집에가고싶다...",
                    "ㅁㅁ동","5","3"
                ),
                Post(
                    213,
                    R.drawable.image_myaccount,
                    "오늘 저희 강아지가", "1", "3","휴지를 다 물어뜯어놨네요. 어떻게이런일이",
                    "ㅎㅎ동","5","3"
                ),
                Post(
                    214,
                    R.drawable.image_myaccount,
                    "구내식당", "1", "3","노맛",
                    "ㅇㅇ동","5","3"
                ),
                Post(215,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㄹㄹ동","5","3"
                ),
                Post(216,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㅂㅂ동","5","3"
                ),
                Post(
                    217,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㅎㅎ동","5","3"
                )

            )
        )
    }


}

