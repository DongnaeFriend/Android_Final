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

class DongnaePassFragment : Fragment() {
    lateinit var binding: FragmentDongnaeshareBinding
    private lateinit var DongnaeshareAdapter: DongnaeshareAdapter
    private val dongnaeshareData = mutableListOf<Post>()


    private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
    private val api: RetrofitInterfaceTommy =
        retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
    //private val authToken =
        //"eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
        private val authToken ="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5Mjk1MzQxMCwiZXhwIjoxNjk0MTYzMDEwfQ.VYuDz4f5lHS8cdkjR4_-YNX9LUzfcMJIMrI_SegYFXJSf5Nch5qNOcoKPWfgq_TvdZOTqXn5chFKpBuks1q4hg"

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDongnaeshareBinding.inflate(inflater, container,false)

        api.getPeed("", 4,0, "createdAt",0,"Bearer $authToken")
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
        DongnaeshareAdapter = DongnaeshareAdapter().apply {
            dataList = dongnaeshareData

            // 아이템 클릭 리스너 설정
            onItemClickListener = object : DongnaeshareAdapter.OnItemClickListener {
                override fun onItemClick(post: Post) {
                    (activity as DongnaeInformationActivity).goneForDetail()

                    val fragmentTransaction = parentFragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.container_main, DongnaeDetailFragment.newInstance(post))
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
            }
        }

        binding.rvDongnaeshare.adapter = DongnaeshareAdapter
    }

    private fun initRestaurantList() {
        dongnaeshareData.addAll(
            listOf<Post>(
                Post(
                    510,
                    R.drawable.pass1,
                    "수박 한통 혼자 있을 때 절때 사지 마세요", "3", "4","지난주일욜날  넘먹고싶어서 한통구매 하나로마트 라인에",
                    "전농","9분","21"
                ),
                Post(
                    511,
                    R.drawable.pass2,
                    "양조절 실패", "5", "6","다들 자취하는데 음식 양조절 실패 해보셨죠?",
                    "전농","12분","39"
                ),
                Post(
                    512,
                    R.drawable.pass3,
                    "첫자취생 종량제봉투", "1", "3","안녕하세요! 제가 이번에 경남에서 올라와 처음 자취를 하게되었는데요",
                    "답십리","28분","12"
                ),
                Post(
                    513,
                    R.drawable.pass4,
                    "바퀴벌레 잡아주세요ㅠ", "8", "0","조사거리 쪽에 자취하는데 바퀴벌레 한마리 큰게 나와서",
                    "면목","2시간","120"
                ),
                Post(
                    514,
                    R.drawable.pass5,
                    "벽걸이 에어컨 청소 업체", "2", "0","자취생인데 겨울쯤 들어와서 전에 쓰던 에어컨을",
                    "장안","3시간","56"
                ),
                Post(
                    515,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㄹㄹ동","5","3"
                ),
                Post(
                    516,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㅂㅂ동","5","3"
                ),
                Post(
                    517,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3","본문입니다",
                    "ㅎㅎ동","5","3"
                )

            )
        )
    }


}

