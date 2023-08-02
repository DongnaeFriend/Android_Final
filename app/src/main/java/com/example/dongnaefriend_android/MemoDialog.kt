package com.example.dongnaefriend_android

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dongnaefriend_android.databinding.DialogAccountmemoBinding


class MemoDialog :DialogFragment(){
    private lateinit var binding:DialogAccountmemoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogAccountmemoBinding.inflate(inflater,container, false)

        //dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT)
        dialog?.setCancelable(false)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window!!.setDimAmount(0.9f)

        val sharedPrefs= requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()


        /*메모 개수 조절(구현실패)
        var memoCount = sharedPrefs.getInt("memocount",1)


        //메모개수 추가
        binding.imageviewMemoplus.setOnClickListener {
            editor.putInt("memocount",memoCount)

            when(memoCount){
                1 -> binding.ConstMemo2.visibility = View.VISIBLE
                2 -> binding.ConstMemo3.visibility = View.VISIBLE
                3 -> binding.ConstMemo4.visibility = View.VISIBLE
                4 -> binding.ConstMemo5.visibility = View.VISIBLE
                5 -> binding.ConstMemo6.visibility = View.VISIBLE
                6 -> binding.ConstMemo7.visibility = View.VISIBLE
                7 -> binding.ConstMemo8.visibility = View.VISIBLE
            }
            memoCount += 1

        }

        //메모개수 감소
        binding.imageviewMemodelete1.setOnClickListener{
            memoCount -= 1
            editor.putInt("memocount",memoCount)
            binding.ConstMemo1.visibility = View.GONE
        }
        binding.imageviewMemodelete2.setOnClickListener{
            memoCount -= 1
            editor.putInt("memocount",memoCount)
            binding.ConstMemo2.visibility = View.GONE
        }
        binding.imageviewMemodelete3.setOnClickListener{
            memoCount -= 1
            editor.putInt("memocount",memoCount)
            binding.ConstMemo3.visibility = View.GONE
        }
        binding.imageviewMemodelete4.setOnClickListener{
            memoCount -= 1
            editor.putInt("memocount",memoCount)
            binding.ConstMemo4.visibility = View.GONE
        }
        binding.imageviewMemodelete5.setOnClickListener{
            memoCount -= 1
            editor.putInt("memocount",memoCount)
            binding.ConstMemo5.visibility = View.GONE
        }
        binding.imageviewMemodelete6.setOnClickListener{
            memoCount -= 1
            editor.putInt("memocount",memoCount)
            binding.ConstMemo6.visibility = View.GONE
        }
        binding.imageviewMemodelete7.setOnClickListener{
            memoCount -= 1
            editor.putInt("memocount",memoCount)
            binding.ConstMemo7.visibility = View.GONE
        }
        binding.imageviewMemodelete8.setOnClickListener{
            memoCount -= 1
            editor.putInt("memocount",memoCount)
            binding.ConstMemo8.visibility = View.GONE
        }//여기까지


        when (memoCount){
            1 -> {
                binding.ConstMemo2.visibility = View.GONE
                binding.ConstMemo3.visibility = View.GONE
                binding.ConstMemo4.visibility = View.GONE
                binding.ConstMemo5.visibility = View.GONE
                binding.ConstMemo6.visibility = View.GONE
                binding.ConstMemo7.visibility = View.GONE
                binding.ConstMemo8.visibility = View.GONE
            }

            2 -> {
                binding.ConstMemo3.visibility = View.GONE
                binding.ConstMemo2.visibility = View.VISIBLE
            }
            3 -> {
                binding.ConstMemo4.visibility = View.GONE
                binding.ConstMemo3.visibility = View.VISIBLE
            }
            4 -> {
                binding.ConstMemo5.visibility = View.GONE
                binding.ConstMemo4.visibility = View.VISIBLE
            }
            5 -> {
                binding.ConstMemo6.visibility = View.GONE
                binding.ConstMemo5.visibility = View.VISIBLE
            }
            6 -> {
                binding.ConstMemo7.visibility = View.GONE
                binding.ConstMemo6.visibility = View.VISIBLE
            }
            7 -> {
                binding.ConstMemo8.visibility = View.GONE
                binding.ConstMemo7.visibility = View.VISIBLE
            }
            8 -> {
                binding.ConstMemo8.visibility = View.VISIBLE
            }

        }

         */







        binding.buttonMemoclose.setOnClickListener{
            editor.putString("memo1",binding.edittextAccountmemo1.text.toString())
            editor.putBoolean("memo1B",binding.checkboxAccountmemo1.isChecked)

            editor.putString("memo2",binding.edittextAccountmemo2.text.toString())
            editor.putBoolean("memo2B",binding.checkboxAccountmemo2.isChecked)

            editor.putString("memo3",binding.edittextAccountmemo3.text.toString())
            editor.putBoolean("memo3B",binding.checkboxAccountmemo3.isChecked)

            editor.putString("memo4",binding.edittextAccountmemo4.text.toString())
            editor.putBoolean("memo4B",binding.checkboxAccountmemo4.isChecked)

            editor.putString("memo5",binding.edittextAccountmemo5.text.toString())
            editor.putBoolean("memo5B",binding.checkboxAccountmemo5.isChecked)

            editor.putString("memo6",binding.edittextAccountmemo6.text.toString())
            editor.putBoolean("memo6B",binding.checkboxAccountmemo6.isChecked)

            editor.putString("memo7",binding.edittextAccountmemo7.text.toString())
            editor.putBoolean("memo7B",binding.checkboxAccountmemo7.isChecked)

            editor.putString("memo8",binding.edittextAccountmemo8.text.toString())
            editor.putBoolean("memo8B",binding.checkboxAccountmemo8.isChecked)
            editor.apply()//sharedpreference에 넣기

            dialog?.dismiss()//메모창닫기
        }

        //sharedpreference에 값이 비어있지 않으면, 적용하기
        if (sharedPrefs.getString("memo1", " ") != " ") {
            binding.edittextAccountmemo1.setText(sharedPrefs.getString("memo1", " "))
            binding.checkboxAccountmemo1.isChecked = sharedPrefs.getBoolean("memo1B", false)}
        if (sharedPrefs.getString("memo2", " ") != " ") {
            binding.edittextAccountmemo2.setText(sharedPrefs.getString("memo2", " "))
            binding.checkboxAccountmemo2.isChecked = sharedPrefs.getBoolean("memo2B", false)}
        if (sharedPrefs.getString("memo3", " ") != " ") {
            binding.edittextAccountmemo3.setText(sharedPrefs.getString("memo3", " "))
            binding.checkboxAccountmemo3.isChecked = sharedPrefs.getBoolean("memo3B", false)}
        if (sharedPrefs.getString("memo4", " ") != " ") {
            binding.edittextAccountmemo4.setText(sharedPrefs.getString("memo4", " "))
            binding.checkboxAccountmemo4.isChecked = sharedPrefs.getBoolean("memo4B", false)}
        if (sharedPrefs.getString("memo5", " ") != " ") {
            binding.edittextAccountmemo5.setText(sharedPrefs.getString("memo5", " "))
            binding.checkboxAccountmemo5.isChecked = sharedPrefs.getBoolean("memo5B", false)}
        if (sharedPrefs.getString("memo6", " ") != " ") {
            binding.edittextAccountmemo6.setText(sharedPrefs.getString("memo6", " "))
            binding.checkboxAccountmemo6.isChecked = sharedPrefs.getBoolean("memo6B", false)}
        if (sharedPrefs.getString("memo7", " ") != " ") {
            binding.edittextAccountmemo7.setText(sharedPrefs.getString("memo7", " "))
            binding.checkboxAccountmemo7.isChecked = sharedPrefs.getBoolean("memo7B", false)}
        if (sharedPrefs.getString("memo8", " ") != " ") {
            binding.edittextAccountmemo8.setText(sharedPrefs.getString("memo8", " "))
            binding.checkboxAccountmemo8.isChecked = sharedPrefs.getBoolean("memo8B", false)}



        return binding.root
    }
}