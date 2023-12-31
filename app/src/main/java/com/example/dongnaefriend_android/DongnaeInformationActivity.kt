package com.example.dongnaefriend_android

import android.content.Intent
import android.os.Bundle
import android.view.View
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


        // image_write의 클릭 리스너 설정
//        binding.imageWrite.setOnClickListener {
//            openDongnaeShareFragment()
//        }

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
                3 -> "정보공유"
                4 -> "같이해요"
                5 -> "소통해요"
                else -> "기타"
            }
        }.attach()

        // BottomNavigationView의 아이템 선택 리스너 설정
        binding.navBottom.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.menu_dongnaeinformation -> {

                    true
                }
                R.id.menu_accountbook -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("text", 4)
                    startActivity(intent)
                    true
                }
                R.id.menu_mypage -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("text", 5)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        binding.navBottom.selectedItemId = R.id.menu_dongnaeinformation




        binding.btnDongnaeshare.setOnClickListener {

            goneForWrite()
            supportFragmentManager
                .beginTransaction()
                .replace(binding.containerDongnaewrite.id, DongnaeWriteFragment())
                .commitAllowingStateLoss()


        }




    }


//    private fun openDongnaeShareFragment() {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(android.R.id.content, DongnaeWriteFragment())
//        transaction.addToBackStack(null)  // "뒤로" 작업
//        transaction.commit()
//    }
    inner class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int {
            return 7
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> DongnaeHomeFragment()
                1 -> DongnaeRestaurantFragment()
                2 -> DongnaeFacilityFragment()
                3 -> DongnaeInformationShareFragment()
                4 -> DongnaeTogetherFragment()
                5 -> DongnaePassFragment()
                else -> DonggnaeEtcFragment()
            }
        }

    }

    fun goneForWrite(){
        binding.containerDongnae.visibility = View.GONE
        binding.toolbarDongnaeinformation.visibility = View.GONE
        binding.RL1.visibility = View.GONE
        binding.tabLayout.visibility = View.GONE
        binding.btnDongnaeshare.visibility = View.GONE
    }

    fun goneForDetail(){
        binding.btnDongnaeshare.visibility = View.GONE
    }

    fun visibleForDetail(){
        binding.btnDongnaeshare.visibility = View.VISIBLE
    }


}
