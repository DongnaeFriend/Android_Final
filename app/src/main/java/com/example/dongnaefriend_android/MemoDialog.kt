package com.example.dongnaefriend_android

import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dongnaefriend_android.Retrofit2.AccountAllResponse
import com.example.dongnaefriend_android.Retrofit2.MemoResponse
import com.example.dongnaefriend_android.Retrofit2.PostBudget
import com.example.dongnaefriend_android.Retrofit2.PostMemo
import com.example.dongnaefriend_android.Retrofit2.RetrofitClient
import com.example.dongnaefriend_android.Retrofit2.RetrofitInterfaceTommy
import com.example.dongnaefriend_android.databinding.DialogAccountmemoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

private val authToken = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
private lateinit var binding:DialogAccountmemoBinding
private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
private val api: RetrofitInterfaceTommy = retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현

class MemoDialog :DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAccountmemoBinding.inflate(inflater,container, false)

        //dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT)
        dialog?.setCancelable(false)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window!!.setDimAmount(0.5f)


        Runnable {
            api.getMemo(2023, 4,"Bearer $authToken").enqueue(object : Callback<MemoResponse> {
                // 전송 실패
                override fun onFailure(call: Call<MemoResponse>, t: Throwable) {
                    Log.d("MemoGet실패", t.message!!)
                }
                // 전송 성공
                override fun onResponse(call: Call<MemoResponse>, response: Response<MemoResponse>) {
                    Log.d("MemoGet성공", "response : ${response.body()?.memos}") // 정상출력

                    var Memos = response.body()?.memos!!



                    var Memo0 = Memos[0]
                    var Memo1 = Memos[1]
                    var Memo2 = Memos[2]
                    var Memo3 = Memos[3]
                    var Memo4 = Memos[4]


                    Log.d("#############", "response : ${Memo1}") // 정상출력
                    Log.d("#############", "response : ${Memo2}") // 정상출력
                    Log.d("#############", "response : ${Memo3}") // 정상출력
                    Log.d("#############", "response : ${Memo4}") // 정상출력



                    //memo 1번째 값
                    var MemoString0 = Memo0.toString()
                    var MemoStringCut0 = MemoString0.split("Memos(memoId=1, memo=",", done=")
                    var MemoStringCutSelect0 = MemoStringCut0[1]
                    var memoChecked0 = MemoStringCut0[2]

                    //memo 2번째 값
                    var MemoString1 = Memo1.toString()
                    var MemoStringCut1 = MemoString1.split("Memos(memoId=2, memo=",", done=")
                    var MemoStringCutSelect1 = MemoStringCut1[1]
                    var memoChecked1 = MemoStringCut1[2]

                    //memo 3번째 값
                    var MemoString2 = Memo2.toString()
                    var MemoStringCut2 = MemoString2.split("Memos(memoId=3, memo=",", done=")
                    var MemoStringCutSelect2 = MemoStringCut2[1]
                    var memoChecked2 = MemoStringCut2[2]

                    //memo 4번째 값
                    var MemoString3 = Memo3.toString()
                    var MemoStringCut3 = MemoString3.split("Memos(memoId=4, memo=",", done=")
                    var MemoStringCutSelect3 = MemoStringCut3[1]
                    var memoChecked3 = MemoStringCut3[2]

                    //memo 5번째 값
                    var MemoString4 = Memo4.toString()
                    var MemoStringCut4 = MemoString4.split("Memos(memoId=5, memo=",", done=")
                    var MemoStringCutSelect4 = MemoStringCut4[1]
                    var memoChecked4 = MemoStringCut4[2]

                    Log.d("###%%%%%#######", "response : ${memoChecked4}") // 정상출력


                    var MemoChecked0 = false
                    var MemoChecked1 = false
                    var MemoChecked2 = false
                    var MemoChecked3 = false
                    var MemoChecked4 = false


                    if(memoChecked0 == "false)") { MemoChecked0 = false}
                    else{ MemoChecked0 = true}

                    if(memoChecked1 == "false)") { MemoChecked1 = false}
                    else{ MemoChecked1 = true}

                    if(memoChecked2 == "false)") { MemoChecked2 = false}
                    else{ MemoChecked2 = true}

                    if(memoChecked3 == "false)") { MemoChecked3 = false}
                    else{ MemoChecked3 = true}

                    if(memoChecked4 == "false)") { MemoChecked4 = false}
                    else{ MemoChecked4 = true}


                    binding.edittextAccountmemo1.setText(MemoStringCutSelect0)
                    binding.edittextAccountmemo2.setText(MemoStringCutSelect1)
                    binding.edittextAccountmemo3.setText(MemoStringCutSelect2)
                    binding.edittextAccountmemo4.setText(MemoStringCutSelect3)
                    binding.edittextAccountmemo5.setText(MemoStringCutSelect4)





                    binding.checkboxAccountmemo1.isChecked = MemoChecked0
                    binding.checkboxAccountmemo2.isChecked = MemoChecked1
                    binding.checkboxAccountmemo3.isChecked = MemoChecked2
                    binding.checkboxAccountmemo4.isChecked = MemoChecked3
                    binding.checkboxAccountmemo5.isChecked = MemoChecked4







                    // 전송은 성공 but 서버 4xx 에러
                    Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                    Log.d("태그: 메시지", "response : ${response.message()}")
                    Log.d("태그: 코드", "response : ${response.code()}")
                }

            })
        }.run()


        /*
        val sharedPrefs= requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        */




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

        val memo1 = binding.edittextAccountmemo1.text.toString()
        val memo2 = binding.edittextAccountmemo2.text.toString()
        val memo3 = binding.edittextAccountmemo3.text.toString()
        val memo4 = binding.edittextAccountmemo4.text.toString()
        val memo5 = binding.edittextAccountmemo5.text.toString()









        binding.buttonMemoclose.setOnClickListener{

            val memo1 = binding.edittextAccountmemo1.text.toString()
            val memo2 = binding.edittextAccountmemo2.text.toString()
            val memo3 = binding.edittextAccountmemo3.text.toString()
            val memo4 = binding.edittextAccountmemo4.text.toString()
            val memo5 = binding.edittextAccountmemo5.text.toString()

            val done1 = binding.checkboxAccountmemo1.isChecked
            val done2 = binding.checkboxAccountmemo2.isChecked
            val done3 = binding.checkboxAccountmemo3.isChecked
            val done4 = binding.checkboxAccountmemo4.isChecked
            val done5 = binding.checkboxAccountmemo5.isChecked





            //포스트 메모
            /*
            val data = PostMemo("dsaf")
            api.postAccountMemo(2023,4,data,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("memo포스트 성공", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "포스트 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "Memo포스트 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })

             */


            val data1 = PostMemo(memo1,done1)
            api.putMemo(1,data1,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("memoPut 성공", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "Put 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "MemoPut 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })

            val data2 = PostMemo(memo2,done2)
            api.putMemo(2,data2,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("memoPut 성공", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "Put 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "MemoPut 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })

            val data3 = PostMemo(memo3,done3)
            api.putMemo(3,data3,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("memoPut 성공", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "Put 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "MemoPut 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })

            val data4 = PostMemo(memo4,done4)
            api.putMemo(4,data4,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("memoPut 성공", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "Put 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "MemoPut 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })

            val data5 = PostMemo(memo5,done5)
            api.putMemo(5,data5,"Bearer $authToken").enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>){
                    Log.d("memoPut 성공", "response :")
                    if (response.isSuccessful.not()){
                        Log.e(ContentValues.TAG, response.toString())
                        return
                    }else{
                        Log.e(ContentValues.TAG, "Put 성공")
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable){
                    Log.e(ContentValues.TAG, "MemoPut 실패")
                    Log.e(ContentValues.TAG, t.toString())
                }
            })


            /*
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

             */



            dialog?.dismiss()//메모창닫기
        }


        /*
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

         */



        return binding.root
    }
}