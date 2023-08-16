package com.example.dongnaefriend_android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.dongnaefriend_android.databinding.FragmentAccountshareWriteBinding

class AccountshareWriteFragment : Fragment() {
    lateinit var binding: FragmentAccountshareWriteBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountshareWriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvComplete.setOnClickListener{

            val intent = Intent(getActivity(), AccountbookActivity::class.java)
            intent.putExtra("text",2)
            startActivity(intent)
        }

        binding.writeAgonize.setOnClickListener {
            initAccountshareWriteList("agonize")
        }

        binding.writeBuy.setOnClickListener {
            initAccountshareWriteList("buy")
        }
        binding.writeTip.setOnClickListener {
            initAccountshareWriteList("tip")
        }
        binding.writeEtc.setOnClickListener {
            initAccountshareWriteList("etc")
        }

        binding.accountshareWriteBack.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.container_fragment,AccountshareFragment()).commit()
            (activity as AccountbookActivity).containerFragmentGone()
        }
    }

    private fun initAccountshareWriteList(s: String) {
        when(s){
            "agonize" ->{
                binding.tvWriteAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.ivWriteAgonize.setImageResource(R.drawable.item_accountshare_category_touched)
                binding.tvWriteBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteBuy.setImageResource(R.drawable.item_accountshare_category_untouched_buy)
                binding.tvWriteTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteTip.setImageResource(R.drawable.item_accountshare_category_untouched)
                binding.tvWriteEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteEtc.setImageResource(R.drawable.item_accountshare_category_untouched)
            }

            "buy" ->{
                binding.tvWriteAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteAgonize.setImageResource(R.drawable.item_accountshare_category_untouched)
                binding.tvWriteBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.ivWriteBuy.setImageResource(R.drawable.item_accountshare_category_touched_buy)
                binding.tvWriteTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteTip.setImageResource(R.drawable.item_accountshare_category_untouched)
                binding.tvWriteEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteEtc.setImageResource(R.drawable.item_accountshare_category_untouched)
            }

            "tip" ->{
                binding.tvWriteAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteAgonize.setImageResource(R.drawable.item_accountshare_category_untouched)
                binding.tvWriteBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteBuy.setImageResource(R.drawable.item_accountshare_category_untouched_buy)
                binding.tvWriteTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.ivWriteTip.setImageResource(R.drawable.item_accountshare_category_touched)
                binding.tvWriteEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteEtc.setImageResource(R.drawable.item_accountshare_category_untouched)
            }

            "etc" ->{
                binding.tvWriteAgonize.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteAgonize.setImageResource(R.drawable.item_accountshare_category_untouched)
                binding.tvWriteBuy.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteBuy.setImageResource(R.drawable.item_accountshare_category_untouched_buy)
                binding.tvWriteTip.setTextColor(ContextCompat.getColor(requireActivity(), R.color.grey))
                binding.ivWriteTip.setImageResource(R.drawable.item_accountshare_category_untouched)
                binding.tvWriteEtc.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
                binding.ivWriteEtc.setImageResource(R.drawable.item_accountshare_category_touched)
            }
        }
    }

}