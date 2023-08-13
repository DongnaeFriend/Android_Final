package com.example.dongnaefriend_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dongnaefriend_android.databinding.ActivityDongnaeinformationBinding
import com.google.android.material.tabs.TabLayoutMediator

class DongnaeInformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDongnaeinformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDongnaeinformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewPager2와 Adapter 연결
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.containerDongnae.adapter = pagerAdapter

        // TabLayoutMediator를 사용하여 TabLayout과 ViewPager2 연결
        TabLayoutMediator(
            binding.tabLayout,
            binding.containerDongnae
        ) { tab, position ->  // containerDongnae를 사용합니다.
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