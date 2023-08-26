package com.example.dongnaefriend_android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dongnaefriend_android.Retrofit2.BudgetResponse
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

class DongnaeRestaurantFragment : Fragment() {
    private lateinit var binding: FragmentDongnaeshareBinding
    private lateinit var adapter: DongnaeshareAdapter
    private var dongnaeshareData = mutableListOf<Post>()



    private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
    private val api: RetrofitInterfaceTommy =
        retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
    //private val authToken =
       // "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
    private val authToken ="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5Mjk1MzQxMCwiZXhwIjoxNjk0MTYzMDEwfQ.VYuDz4f5lHS8cdkjR4_-YNX9LUzfcMJIMrI_SegYFXJSf5Nch5qNOcoKPWfgq_TvdZOTqXn5chFKpBuks1q4hg"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





            api.getPeed("", 0,0, "createdAt",0,"Bearer $authToken")
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



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDongnaeshareBinding.inflate(inflater, container, false)







        return binding.root
    }

    private fun initRestaurantRecyclerView() {
        binding.rvDongnaeshare.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = DongnaeshareAdapter().apply {
            dataList = dongnaeshareData
            onItemClickListener = object : DongnaeshareAdapter.OnItemClickListener {
                override fun onItemClick(post: Post) {
                    // TODO: 여기서 fragment_dongnae_detail.xml을 보여주는 프래그먼트로 전환해야 합니다.
                    (activity as DongnaeInformationActivity).goneForDetail()
                    // 예:
                    val fragmentTransaction = parentFragmentManager.beginTransaction()
                    fragmentTransaction.replace(
                        R.id.container_main,
                        DongnaeDetailFragment.newInstance(post)
                    )
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
            }
        }
        binding.rvDongnaeshare.adapter = adapter
    }


    private fun initRestaurantList() {
        dongnaeshareData.addAll(
            listOf<Post>(

                Post(
                    110,
                    R.drawable.restaurant_1,
                    "구 바우하우스 주변 24시 식당", "0", "0", "알바 퇴근이 늦은데 집가서 요리해먹기 너무 귀찮네요.. ",
                    "장안", "4분", "22"
                ),
                Post(
                    111,
                    R.drawable.restaurant_2,
                    "드릅이나 오가피순무침 같은거 나물파는", "1", "3", "반찬가게찾아요 아시는분계실까요",
                    "전농", "15분", "30"
                ),
                Post(
                    112,
                    R.drawable.restaurant_3,
                    "무한리필", "2", "0", "무한리필 식사하실 분? 명륜진사갈비 혼밥 가능할까요?",
                    "장안", "18분", "22"
                ),
                Post(
                    113,
                    R.drawable.restaurant_4,
                    "혼밥혼술 식당", "6", "4", "튼튼병원 앞 상호명이 혼밥혼술 식당",
                    "휘경", "34분", "50"
                ),
                Post(
                    114,
                    R.drawable.restaurant_5,
                    "남다른 초밥", "16", "0", "골목안에 이런집이?",
                    "전농", "4시간", "558"
                ),
                Post(
                    115,
                    R.drawable.restaurant_6,
                    "장안사거리 근처 근사한 한정식집 혼밥 후기\n" ,
                    "5", "0", "별점 다섯개 박을게요",
                    "장안", "3일", "464"
                ),
                Post(
                    116,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3", "본문입니다",
                    "ㅂㅂ동", "5", "3"
                ),
                Post(
                    117,
                    R.drawable.image_myaccount,
                    "안녕하세요", "1", "3", "본문입니다",
                    "ㅎㅎ동", "5", "3"
                ),


                )
        )
    }

}
