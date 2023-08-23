package com.example.dongnaefriend_android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaefriend_android.adapter.AccountshareAdapter
import com.example.dongnaefriend_android.adapter.DongnaeshareAdapter
import com.example.dongnaefriend_android.databinding.FragmentAccountshareBinding
import model.AccountsharePost
import model.Post

class AccountshareFragment : Fragment() {
    lateinit var binding: FragmentAccountshareBinding
    private lateinit var accountshareAdapter: AccountshareAdapter
    private val accountshareData = mutableListOf<AccountsharePost>()
    val TAG = "post"

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

        arguments?.let {
            val postType = it.getString("postType")
            if (postType != null) {
                initAccountshareList(postType)
                initAccountshareRecyclerView()
            }
        }


        initAccountshareList("all")
        initAccountshareRecyclerView()

        binding.tvAccountshareAll.setOnClickListener {
            initAccountshareList("all")
            initAccountshareRecyclerView()
        }
        binding.tvAccountshareAgonize.setOnClickListener {
            initAccountshareList("agonize")
            initAccountshareRecyclerView()
        }
        binding.tvAccountshareBuy.setOnClickListener {
            initAccountshareList("buy")
            initAccountshareRecyclerView()
        }
        binding.tvAccountshareTip.setOnClickListener {
            initAccountshareList("tip")
            initAccountshareRecyclerView()
        }
        binding.tvAccountshareEtc.setOnClickListener {
            initAccountshareList("etc")
            initAccountshareRecyclerView()
        }


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
        accountshareAdapter.onItemClickListener = object :
            AccountshareAdapter.OnAccountshareItemClickListener {
            override fun onItemClicked(post: AccountsharePost) {
                // 여기서 AccountshareDetailFragment로 이동하도록 처리
                val fragment = AccountshareDetailFragment().apply {
                    arguments = Bundle().apply {
                        // 여기서 필요한 정보를 전달
                        putSerializable("post_detail", post)
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container_account_detail, fragment)
                    .addToBackStack(null) // 이전 Fragment로 돌아오기 위해
                    .commit()
            }
        }
    }


    private fun initAccountshareList(postType: String) {
        Log.d("post", "postType:"+postType)

        accountshareData.clear()


        when (postType){
            "all" -> {
                binding.tvAccountshareAll.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.tvAccountshareAll.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.purple))
                binding.tvAccountshareAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareAgonize.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareBuy.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareTip.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareEtc.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                initAccountshareListAll()
            }
            "agonize" -> {
                binding.tvAccountshareAll.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareAll.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.tvAccountshareAgonize.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.purple))
                binding.tvAccountshareBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareBuy.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareTip.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareEtc.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                initAccountshareListAgonize()
            }
            "buy" -> {
                binding.tvAccountshareAll.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareAll.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareAgonize.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.tvAccountshareBuy.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.purple))
                binding.tvAccountshareTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareTip.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareEtc.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                initAccountshareListBuy()
            }
            "tip" -> {
                binding.tvAccountshareAll.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareAll.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareAgonize.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareBuy.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.tvAccountshareTip.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.purple))
                binding.tvAccountshareEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareEtc.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                initAccountshareListTip()
            }
            "etc" -> {
                binding.tvAccountshareAll.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareAll.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareAgonize.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareBuy.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
                binding.tvAccountshareTip.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.white))
                binding.tvAccountshareEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.tvAccountshareEtc.setBackgroundColor(ContextCompat.getColor(requireActivity(),R.color.purple))
                initAccountshareListEtc()
            }
        }
    }


    private fun initAccountshareListAll() {
        accountshareData.addAll(
            listOf<AccountsharePost>(
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "안녕하세요", "0", "0","본문입니다",
                    "1","0",R.drawable.item_accountshare_category_untouched,"기타"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "학교근처맛집", "0", "2","학교 근처에 먹을게 없어요 배고파요",
                    "2","5",R.drawable.item_accountshare_category_untouched,"고민"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "출근중인데", "1", "3","집에가고싶다...",
                    "3","5",R.drawable.item_accountshare_category_untouched,"고민"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "서x웨이 싸게 먹는법", "3", "5","서x웨이 싸게 먹는 법 알려드립니다",
                    "5","4",R.drawable.item_accountshare_category_untouched,"꿀팁"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "구내식당", "1", "3","노맛",
                    "7","13",R.drawable.item_accountshare_category_untouched,"기타"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "oo콘 vs xx깡", "15", "8","뭐 살까요",
                    "9","50",R.drawable.item_accountshare_category_untouched,"살까말까"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "지금 oo마켓 할인행사", "1", "4","xx아파트 길건너 마트 행사하네요",
                    "13","45",R.drawable.item_accountshare_category_untouched,"기타"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "돈을 너무 많이 썼어요", "5", "7","예산 초과가...",
                    "15","29",R.drawable.item_accountshare_category_untouched,"고민"
                )

            )
        )
    }
    private fun initAccountshareListAgonize() {
        accountshareData.addAll(
            listOf<AccountsharePost>(
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "학교근처맛집", "0", "2","학교 근처에 먹을게 없어요 배고파요",
                    "2","5",R.drawable.item_accountshare_category_untouched,"고민"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "출근중인데", "1", "3","집에가고싶다...",
                    "3","5",R.drawable.item_accountshare_category_untouched,"고민"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "돈을 너무 많이 썼어요", "5", "7","예산 초과가...",
                    "15","29",R.drawable.item_accountshare_category_untouched,"고민"
                )

            )
        )
    }
    private fun initAccountshareListBuy() {
        accountshareData.addAll(
            listOf<AccountsharePost>(
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "oo콘 vs xx깡", "15", "8","뭐 살까요",
                    "9","50",R.drawable.item_accountshare_category_untouched,"살까말까"
                )
            )
        )
    }
    private fun initAccountshareListTip() {
        accountshareData.addAll(
            listOf<AccountsharePost>(
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "서x웨이 싸게 먹는법", "3", "5","서x웨이 싸게 먹는 법 알려드립니다",
                    "5","4",R.drawable.item_accountshare_category_untouched,"꿀팁"
                )

            )
        )
    }
    private fun initAccountshareListEtc() {
        accountshareData.addAll(
            listOf<AccountsharePost>(
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "안녕하세요", "0", "0","본문입니다",
                    "1","0",R.drawable.item_accountshare_category_untouched,"기타"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "구내식당", "1", "3","노맛",
                    "7","13",R.drawable.item_accountshare_category_untouched,"기타"
                ),
                AccountsharePost(
                    R.drawable.img_accountshare_dummy,
                    "지금 oo마켓 할인행사", "1", "4","xx아파트 길건너 마트 행사하네요",
                    "13","45",R.drawable.item_accountshare_category_untouched,"기타"
                )
            )
        )
    }

}