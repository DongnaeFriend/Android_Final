package com.example.dongnaefriend_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dongnaefriend_android.databinding.ActivityDongnaeinformationBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DongnaeInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDongnaeinformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDongnaeinformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // image_write의 클릭 리스너 설정
        binding.imageWrite.setOnClickListener {
            openDongnaeShareFragment()
        }

        // ViewPager2와 Adapter 연결
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.containerDongnae.adapter = pagerAdapter

        // TabLayoutMediator를 사용하여 TabLayout과 ViewPager2 연결
        TabLayoutMediator(
            binding.tabLayout,
            binding.containerDongnae
        ) { tab, position ->
            tab.text = when (position) {
                0 -> "홈"
                1 -> "맛집"
                2 -> "시설"
                3 -> "할인"
                4 -> "같이해요"
                5 -> "질문/요청"
                else -> "공공정보"
            }
        }.attach()

        // TabLayout에 탭 선택 리스너 추가
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) { // '홈' 탭이 선택되었을 때
                    binding.imageWrite.visibility = View.GONE // 'image_write' 를 숨김
                } else {
                    binding.imageWrite.visibility = View.VISIBLE // 그 외 탭이 선택되었을 때 'image_write' 를 표시
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 필요 없음
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 필요 없음
            }
        })
    }

    private fun openDongnaeShareFragment() {
        Log.d("FragmentTransaction", "openDongnaeShareFragment is called.")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(android.R.id.content, DongnaeWriteFragment())
        transaction.addToBackStack(null)  // "뒤로" 작업
        transaction.commit()
    }
    inner class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int {
            return 7
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> DongnaeHomeFragment()
                1 -> DongnaeRestaurantFragment()
                2 -> DongnaeFacilityFragment()
                3 -> DongnaeDiscountFragment()
                4 -> DongnaeTogetherFragment()
                5 -> DongnaeQuestionFragment()
                else -> DongnaePublicInfoFragment()
            }
        }
    }
}
