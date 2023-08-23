import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.dongnaefriend_android.AccountbookActivity
import com.example.dongnaefriend_android.DongnaeInformationActivity
import com.example.dongnaefriend_android.DongnaeRestaurantFragment
import com.example.dongnaefriend_android.R
import com.example.dongnaefriend_android.Retrofit2.BudgetResponse
import com.example.dongnaefriend_android.Retrofit2.PeedDetailResponse
import com.example.dongnaefriend_android.Retrofit2.RetrofitClient
import com.example.dongnaefriend_android.Retrofit2.RetrofitInterfaceTommy
import com.example.dongnaefriend_android.databinding.FragmentDongnaeDetailBinding
import model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DongnaeDetailFragment : Fragment() {
    private var post: Post? = null
    private lateinit var binding : FragmentDongnaeDetailBinding


    private val retrofit: Retrofit = RetrofitClient.getInstance() // RetrofitClient의 instance 불러오기
    private val api: RetrofitInterfaceTommy =
        retrofit.create(RetrofitInterfaceTommy::class.java) // retrofit이 interface 구현
    private val authToken =
        "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            post = it.getSerializable(ARG_POST) as? Post
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDongnaeDetailBinding.inflate(layoutInflater)




        var postArray = post.toString().split("Post(",", ",")")
        var sequence = postArray[1]
        var Sequence = postArray[1].split("=")[1]



        api.getPeedDetail(Sequence.toInt(),"Bearer $authToken").enqueue(object : Callback<PeedDetailResponse> {
            // 전송 실패
            override fun onFailure(call: Call<PeedDetailResponse>, t: Throwable) {
                Log.d("게시물 상세 실패", t.message!!)
            }
            // 전송 성공
            override fun onResponse(call: Call<PeedDetailResponse>, response: Response<PeedDetailResponse>) {
                Log.d("게시물 상세 성공", "response : ${response.body()?.title}") // 정상출력

                if(Sequence.toInt()<100) {

                    binding.reviewTextView.text = response.body()?.title
                    binding.reviewContentTextView.text = response.body()?.content
                    binding.infoTextView.text =
                        "${response.body()?.createdAt}분전 | 조회 ${response.body()?.view} | 공감 0 ${response.body()?.placeLocation}"
                    binding.locationTextView.text = response.body()?.placeLocation
                }



                // 전송은 성공 but 서버 4xx 에러
                Log.d("태그: 에러바디", "response : ${response.errorBody()}")
                Log.d("태그: 메시지", "response : ${response.message()}")
                Log.d("태그: 코드", "response : ${response.code()}")
            }

        })

        if (sequence == "sequence=1000"){
            binding.reviewTextView.text = "구 바우하우스 주변 24시 식당"
            binding.reviewContentTextView.text = "알바 퇴근이 늦은데 집가서 요리해먹기 너무 귀찮네요.. \n" +
                    "구 바우하우스 근처에 24시 식당 괜찮은 곳 추천해주실 수 있나요?국밥은 너무 질려서 ㅠㅠ 기사식당 같은 곳도 괜찮아용!!"
            binding.infoTextView.text = "4분전 | 조회 22 | 공감 장안동"
            binding.detailImageView.setImageResource(R.drawable.restaurant_1)
            binding.locationTextView.text = "장안동"

        }

        if (sequence == "sequence=110"){
            binding.reviewTextView.text = "구 바우하우스 주변 24시 식당"
            binding.reviewContentTextView.text = "알바 퇴근이 늦은데 집가서 요리해먹기 너무 귀찮네요.. \n" +
                    "구 바우하우스 근처에 24시 식당 괜찮은 곳 추천해주실 수 있나요?국밥은 너무 질려서 ㅠㅠ 기사식당 같은 곳도 괜찮아용!!"
            binding.infoTextView.text = "4분전 | 조회 22 | 댓글 0 | 공감 0"
            binding.locationTextView.text="장안동"
            binding.iconImageView.setImageResource(R.drawable.profile_1)
            binding.detailImageView.setImageResource(R.drawable.restaurant_1)
            binding.locationTextView.text = "장안동"

        }
        if (sequence == "sequence=111"){
            binding.reviewTextView.text = "드릅이나 오가피순무침 같은거 나물파는\n반찬가게 있을까요?"
            binding.reviewContentTextView.text = "반찬가게찾아요 아시는분계실까요"
            binding.infoTextView.text = "15분전 | 조회 30 | 댓글 1 | 공감 3"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_2)
            binding.detailImageView.setImageResource(R.drawable.restaurant_2)

        }
        if (sequence == "sequence=112"){
            binding.reviewTextView.text = "무한리필"
            binding.reviewContentTextView.text = "무한리필 식사하실 분? 명륜진사갈비 혼밥 가능할까요?"
            binding.infoTextView.text = "18분전 | 조회 22 | 댓글 2 | 공감 0"
            binding.locationTextView.text="장안동"
            binding.iconImageView.setImageResource(R.drawable.profile_3)
            binding.detailImageView.setImageResource(R.drawable.restaurant_3)

        }
        if (sequence == "sequence=113"){
            binding.reviewTextView.text = "혼밥혼술 식당"
            binding.reviewContentTextView.text = "튼튼병원 앞 상호명이 혼밥혼술 식당\n"+
                    "너무 저렴하고 집밥같아서 소개해드려요\n"+"밑반찬도 맛있고 안짜고좋네요^^\n"+"제가 좋아하는 계란말이 매일 나온다네요\n"+"밥은 무한으로 먹을 수 있고\n"+"소식하는 분은 사진상 저처럼 한번에 많이드시지마시고 드시고 더드세요\n"+"가격이 5천원부터~ 가장 비싼가격이 6천원이라 부담없고 혼밥하시는 분들도 많이오시네요ㅋ"
            binding.infoTextView.text = "34분전 | 조회 50 | 댓글 6 | 공감 4"
            binding.locationTextView.text="휘경동"
            binding.iconImageView.setImageResource(R.drawable.profile_4)
            binding.detailImageView.setImageResource(R.drawable.restaurant_4)

        }
        if (sequence == "sequence=114"){
            binding.reviewTextView.text = "남다른 초밥"
            binding.reviewContentTextView.text = "골목안에 이런집이?\n"+
                    "혼밥하기 쥑이네~\n\n"+"우연히 지나가다 들렸는데 점심특선 9800원\n"+"점심스페셜은 13000원 이건 양이\n"+"좀 많을듯해서 9800원짜리 점심 메뉴 픽!\n\n"+"괜.찮.다!^^\n"
            binding.infoTextView.text = "4시간전 | 조회 558 | 댓글 16 | 공감 0"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_5)
            binding.detailImageView.setImageResource(R.drawable.restaurant_5)

        }
        if (sequence == "sequence=115"){
            binding.reviewTextView.text = "장안사거리 근처 근사한 한정식집 혼밥 후기"
            binding.reviewContentTextView.text = "별점 다섯개 박을게요\n"
            binding.infoTextView.text = "3일전 | 조회 464 | 댓글 5 | 공감 0"
            binding.locationTextView.text="장안동"
            binding.iconImageView.setImageResource(R.drawable.profile_6)
            binding.detailImageView.setImageResource(R.drawable.restaurant_6)

        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        private const val ARG_POST = "arg_post"

        fun newInstance(post: Post): DongnaeDetailFragment {
            val fragment = DongnaeDetailFragment()
            val bundle = Bundle().apply {
                putSerializable(ARG_POST, post)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as DongnaeInformationActivity).visibleForDetail()
    }
}
