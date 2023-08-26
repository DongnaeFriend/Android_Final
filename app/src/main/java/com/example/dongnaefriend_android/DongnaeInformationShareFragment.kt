package com.example.dongnaefriend_android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaefriend_android.Retrofit2.PeedResponse
import com.example.dongnaefriend_android.Retrofit2.RetrofitClient
import com.example.dongnaefriend_android.Retrofit2.RetrofitInterfaceTommy
import com.example.dongnaefriend_android.adapter.DongnaeshareAdapter
import com.example.dongnaefriend_android.databinding.FragmentDongnaeshareBinding
import model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DongnaeInformationShareFragment : Fragment() {
    lateinit var binding: FragmentDongnaeshareBinding
    private lateinit var DongnaeshareAdapter: DongnaeshareAdapter
    private val dongnaeshareData = mutableListOf<Post>()



    private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
    private val api: RetrofitInterfaceTommy =
        retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
    //private val authToken =
      //  "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
    private val authToken ="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5Mjk1MzQxMCwiZXhwIjoxNjk0MTYzMDEwfQ.VYuDz4f5lHS8cdkjR4_-YNX9LUzfcMJIMrI_SegYFXJSf5Nch5qNOcoKPWfgq_TvdZOTqXn5chFKpBuks1q4hg"


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDongnaeshareBinding.inflate(inflater, container, false)

        api.getPeed("", 2,0, "createdAt",0,"Bearer $authToken")
            .enqueue(object : Callback<PeedResponse> {

                // 전송 실패
                override fun onFailure(call: Call<PeedResponse>, t: Throwable) {
                    Log.d("피드 get 실패", t.message!!)
                }

                // 전송 성공
                override fun onResponse(
                    call: Call<PeedResponse>,
                    response: Response<PeedResponse>
                ) {

                    Log.d("피드 get", "response : ${response.body()?.contents}") // 정상출력

                    var size = 0
                    var peedId = 0
                    var town = ""
                    var title = ""
                    var content = ""
                    var createdAt = ""
                    var view = ""
                    var commentCount = ""
                    var likes = ""

                    var Response = response.body()?.contents!!
                    size = Response.size


                    if(size>=5) {
                        var peed4 = Response[4].toString()
                        var split = peed4.split("Contents(",", ",")")
                        Log.d("split!!!!!!",split.toString())



                        peedId = split[1].split("=")[1].toInt()

                        town = ""
                        if(split[2]==null) {
                            town = "동네를 입력해주세요"
                        }
                        else{
                            town = split[2].split("=")[1]
                        }

                        title = ""
                        if(split[4] == null){
                            title = "제목을 입력해주세요"
                        }
                        else{
                            title = split[4].split("=")[1]
                        }

                        content= ""
                        if(split[5] == null){
                            content = "내용을 입력해주세요"
                        }
                        else{
                            content= split[5].split("=")[1]
                        }
                        createdAt = split[7].split("=")[1]
                        view = split[8].split("=")[1]
                        commentCount = split[9].split("=")[1]
                        likes = split[10].split("=")[1]


                        dongnaeshareData.addAll(
                            listOf<Post>(
                                Post(
                                    peedId,
                                    R.drawable.img_accountshare_dummy,
                                    title, commentCount, likes, content,
                                    town, createdAt, view
                                )

                            )
                        )


                    }

                    if(size>=4) {
                        var peed3 = Response[3].toString()
                        var split = peed3.split("Contents(",", ",")")
                        Log.d("split!!!!!!",split.toString())



                        peedId = split[1].split("=")[1].toInt()

                        town = ""
                        if(split[2]==null) {
                            town = "동네를 입력해주세요"
                        }
                        else{
                            town = split[2].split("=")[1]
                        }

                        title = ""
                        if(split[4] == null){
                            title = "제목을 입력해주세요"
                        }
                        else{
                            title = split[4].split("=")[1]
                        }

                        content= ""
                        if(split[5] == null){
                            content = "내용을 입력해주세요"
                        }
                        else{
                            content= split[5].split("=")[1]
                        }
                        createdAt = split[7].split("=")[1]
                        view = split[8].split("=")[1]
                        commentCount = split[9].split("=")[1]
                        likes = split[10].split("=")[1]


                        dongnaeshareData.addAll(
                            listOf<Post>(
                                Post(
                                    peedId,
                                    R.drawable.img_accountshare_dummy,
                                    title, commentCount, likes, content,
                                    town, createdAt, view
                                )

                            )
                        )


                    }






                    if(size>=3) {
                        var peed2 = Response[2].toString()
                        var split = peed2.split("Contents(",", ",")")
                        Log.d("split!!!!!!",split.toString())



                        peedId = split[1].split("=")[1].toInt()

                        town = ""
                        if(split[2]==null) {
                            town = "동네를 입력해주세요"
                        }
                        else{
                            town = split[2].split("=")[1]
                        }

                        title = ""
                        if(split[4] == null){
                            title = "제목을 입력해주세요"
                        }
                        else{
                            title = split[4].split("=")[1]
                        }

                        content= ""
                        if(split[5] == null){
                            content = "내용을 입력해주세요"
                        }
                        else{
                            content= split[5].split("=")[1]
                        }
                        createdAt = split[7].split("=")[1]
                        view = split[8].split("=")[1]
                        commentCount = split[9].split("=")[1]
                        likes = split[10].split("=")[1]


                        dongnaeshareData.addAll(
                            listOf<Post>(
                                Post(
                                    peedId,
                                    R.drawable.img_accountshare_dummy,
                                    title, commentCount, likes, content,
                                    town, createdAt, view
                                )

                            )
                        )


                    }


                    if(size>=2) {
                        var peed1 = Response[1].toString()
                        var split = peed1.split("Contents(",", ",")")
                        Log.d("split!!!!!!",split.toString())



                        peedId = split[1].split("=")[1].toInt()

                        town = ""
                        if(split[2]==null) {
                            town = "동네를 입력해주세요"
                        }
                        else{
                            town = split[2].split("=")[1]
                        }

                        title = ""
                        if(split[4] == null){
                            title = "제목을 입력해주세요"
                        }
                        else{
                            title = split[4].split("=")[1]
                        }

                        content= ""
                        if(split[5] == null){
                            content = "내용을 입력해주세요"
                        }
                        else{
                            content= split[5].split("=")[1]
                        }
                        createdAt = split[7].split("=")[1]
                        view = split[8].split("=")[1]
                        commentCount = split[9].split("=")[1]
                        likes = split[10].split("=")[1]


                        dongnaeshareData.addAll(
                            listOf<Post>(
                                Post(
                                    peedId,
                                    R.drawable.img_accountshare_dummy,
                                    title, commentCount, likes, content,
                                    town, createdAt, view
                                )

                            )
                        )


                    }


                    if(size>=1) {
                        var peed0 = Response[0].toString()
                        var split = peed0.split("Contents(",", ",")")
                        Log.d("split!!!!!!",split.toString())



                        peedId = split[1].split("=")[1].toInt()

                        town = ""
                        if(split[2]==null) {
                            town = "동네를 입력해주세요"
                        }
                        else{
                            town = split[2].split("=")[1]
                        }

                        title = ""
                        if(split[4] == null){
                            title = "제목을 입력해주세요"
                        }
                        else{
                            title = split[4].split("=")[1]
                        }

                        content= ""
                        if(split[5] == null){
                            content = "내용을 입력해주세요"
                        }
                        else{
                            content= split[5].split("=")[1]
                        }
                        createdAt = split[7].split("=")[1]
                        view = split[8].split("=")[1]
                        commentCount = split[9].split("=")[1]
                        likes = split[10].split("=")[1]


                        dongnaeshareData.addAll(
                            listOf<Post>(
                                Post(
                                    peedId,
                                    R.drawable.img_accountshare_dummy,
                                    title, commentCount, likes, content,
                                    town, createdAt, view
                                )

                            )
                        )


                    }

                    initRestaurantList()
                    initRestaurantRecyclerView()




                    // 전송은 성공 but 서버 4xx 에러
                    Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                    Log.d("태그: 메시지", "response : ${response.message()}")
                    Log.d("태그: 코드", "response : ${response.code()}")
                }

            })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun initRestaurantRecyclerView() {
        binding.rvDongnaeshare.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        DongnaeshareAdapter = DongnaeshareAdapter()
        DongnaeshareAdapter.dataList = dongnaeshareData

        // 아이템 클릭 리스너 설정
        DongnaeshareAdapter.onItemClickListener = object : DongnaeshareAdapter.OnItemClickListener {
            override fun onItemClick(post: Post) {

                (activity as DongnaeInformationActivity).goneForDetail()

                val fragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post))
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
                    310,
                    R.drawable.info_1,
                    "청년1인가구 재무교육안내", "14", "0","신청기간: 2023. 9. 6까지",
                    "전농","4일","680"
                ),
                Post(311,
                    R.drawable.info_2,
                    "동대문오랑 청년 1인가구 집을 고쳐준대요!", "12", "0","동대문구에 거주하는 1인 가구 청년을 대상으로 동대문 오랑에서",
                    "전농","4일","420"
                ),
                Post(
                    312,
                    R.drawable.info_3,
                    "민방위", "1", "5","오늘 훈련문자 왔어요 참고하세요",
                    "전농","3시간","35"
                ),
                Post(
                    313,
                    R.drawable.info_4,
                    "새로 오픈해서 50% 할인하네요~", "2", "1","라미 스킨케어 첫방문 50% 할인이래요 저도 가보려고요 ~",
                    "장안","4시간","50"
                ),
                Post(
                    313,
                    R.drawable.info_5,
                    "함께해요 동대문구 예술인들의 일상 문화 페스티벌", "1", "6","10월 10일은 동대문구 예술인들의",
                    "답십리","6시간","230"
                ),
                Post(
                    315,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㄹㄹ동","5","3"
                ),
                Post(
                    316,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㅂㅂ동","5","3"
                ),
                Post(
                    317,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㅎㅎ동","5","3"
                )

            )
        )
    }


}

