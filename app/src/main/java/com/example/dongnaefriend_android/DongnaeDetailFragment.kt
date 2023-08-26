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
   // private val authToken =
   //     "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5MTY1OTYyMCwiZXhwIjoxNjkyODY5MjIwfQ.07mX0VVFwmoo8nrUvEUvPzF1NMzYSSeMGxgazzN7Upis3F9bRYnZ-15odkvfpsLj1nBKVjRCHLREgttkp1EcdQ"
   private val authToken ="eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjUsImlhdCI6MTY5Mjk1MzQxMCwiZXhwIjoxNjk0MTYzMDEwfQ.VYuDz4f5lHS8cdkjR4_-YNX9LUzfcMJIMrI_SegYFXJSf5Nch5qNOcoKPWfgq_TvdZOTqXn5chFKpBuks1q4hg"


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
            //binding.NickTextView.text = ""
            binding.reviewTextView.text = "남다른 초밥"
            binding.reviewContentTextView.text = "골목안에 이런집이?\n"+
            "혼밥하기 쥑이네~\n\n"+"우연히 지나가다 들렸는데 점심특선 9800원\n"+"점심스페셜은 13000원 이건 양이\n"+"좀 많을듯해서 9800원짜리 점심 메뉴 픽!\n\n"+"괜.찮.다!^^\n"
            binding.infoTextView.text = "4시간전 | 조회 464 | 전농동"
            binding.detailImageView.setImageResource(R.drawable.restaurant_5)
            binding.locationTextView.text = "전농동"

        }

        if (sequence == "sequence=1001"){
            binding.reviewTextView.text = "장안사거리 근처 가족식사 장소 추천드립니다"
            binding.reviewContentTextView.text = "한정식 집인데 찬도 잘 나오고 괜찮네요!!"
            binding.infoTextView.text = "3일전 | 조회 464 | 장안동"
            binding.detailImageView.setImageResource(R.drawable.restaurant_6)
            binding.locationTextView.text = "장안동"

        }

        if (sequence == "sequence=1002"){
            binding.reviewTextView.text = "장한평역 스포애니 같이 등록하실분!"
            binding.reviewContentTextView.text = "헬스 등록하려 했는데 이벤트가 있네요!" +
                    "같이 등록하시면 혜택이 있는데 혹시 하실 분 계실까요?"
            binding.infoTextView.text = "1일전 | 조회 214 | 휘경동"
            binding.detailImageView.setImageResource(R.drawable.facility_1)
            binding.locationTextView.text = "휘경동"

        }

        if (sequence == "sequence=1003"){
            binding.reviewTextView.text = "혹시 전사 근처에 카드 되는 빨래방 있을까요?"
            binding.reviewContentTextView.text = "건조기 카드로 돌리고 싶은데 아무리 봐도 없네요..."
            binding.infoTextView.text = "8일전 | 조회 144 | 전농동"
            binding.detailImageView.setImageResource(R.drawable.facility_2)
            binding.locationTextView.text = "전농동"

        }


        if (sequence == "sequence=1004"){
            binding.reviewTextView.text = "청년1인가구 재무교육안내"
            binding.reviewContentTextView.text = "\uD83D\uDD25신청기간: 2023. 9. 6까지\n" +
                    "\uD83C\uDFC3\u200D♀️교육일시: \n" +
                    "1차시 2023. 9. 12. 19:00 ~ 21:00\n" +
                    "2차시 2023. 9. 19. 19:00 ~ 21:00\n" +
                    "\uD83D\uDCBB 내용:\n" +
                    "1차시 - 평생월급 국민연금\n" +
                    "2차시 - 합리적인 경제생활과 금융사기예방\n" +
                    "\uD83D\uDC65 대상: 서울 생활권 20~40대 청년1인가구(동대문구 생활권 우선지원)\n" +
                    "\uD83C\uDFD8️ 장소: 동대문구가족센터 4층 다목적강당\n" +
                    "❓ 문의: 동대문구1인가구지원센터 (070-1234-1234)\n"
            binding.infoTextView.text = "4일전 | 조회 680 | 전농동"
            binding.detailImageView.setImageResource(R.drawable.info_1)
            binding.locationTextView.text = "전농동"

        }

        if (sequence == "sequence=1005"){
            binding.reviewTextView.text = "동대문오랑 청년 1인가구 집을 고쳐준대요!"
            binding.reviewContentTextView.text = "동대문구에 거주하는 1인 가구 청년을 대상으로 동대문 오랑에서 주거 개선 서비스를 제공한다고 해요!!\n" +
                    "\n" +
                    "1인가구 분들 꼭 챙기시길 \uD83E\uDD70\n" +
                    "좋은 정보 있어서 공유합니다~\n" +
                    "\n" +
                    "22,000원 정도는 부담해야 한다고 해요.\n" +
                    "그래도최고네요!\n" +
                    "\n" +
                    "신청 방법: 구글폼 작성, 홍보물 참고\n" +
                    "문의: 02.908.1234 매니저 김석주"
            binding.infoTextView.text = "4일전 | 조회 420 | 전농동"
            binding.detailImageView.setImageResource(R.drawable.info_2)
            binding.locationTextView.text = "전농동"

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
        if (sequence == "sequence=210"){
            binding.reviewTextView.text = "휘경동 교통사고 병원"
            binding.reviewContentTextView.text = "동네친구 동친분들께 휘경동 사거리 근처 주변에 교통사고\n"+"전문으로 입원실 갖춰진 병원좀 추천 부탁 드립니다."
            binding.infoTextView.text = "12분전 | 조회 12 | 댓글 1 | 공감 0"
            binding.locationTextView.text="휘경동"
            binding.iconImageView.setImageResource(R.drawable.profile_1)
            binding.detailImageView.setImageResource(R.drawable.image_myaccount)

        }

        if (sequence == "sequence=211"){
            binding.reviewTextView.text = "시립대 근처에 농구연습 혼자 할만한 곳 있을까요"
            binding.reviewContentTextView.text = "시립대 근처에 농구연습 혼자 할만한 곳 있을까요?\n"+"아파트 단지 내도 상관 없어요 농구공은 있습니다"
            binding.infoTextView.text = "36분전 | 조회 26 | 댓글 3 | 공감 0"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_2)
            binding.detailImageView.setImageResource(R.drawable.fac2)

        }
        if (sequence == "sequence=212"){
            binding.reviewTextView.text = "이마트에서 장봤는데"
            binding.reviewContentTextView.text = "근거리 배송 끝나서 좌절.. 혼자 봐서 큰일 ㅠ\n"+"혼자 사시는 분들 시간 주의해서 마트가세요"
            binding.infoTextView.text = "3시간전 | 조회 80 | 댓글 0 | 공감 4"
            binding.locationTextView.text="장안동"
            binding.iconImageView.setImageResource(R.drawable.profile_3)
            binding.detailImageView.setImageResource(R.drawable.fac3)

        }
        if (sequence == "sequence=213"){
            binding.reviewTextView.text = "동원 짜장라볶이 할인"
            binding.reviewContentTextView.text = "강북구청 사거리 수유마트 동원 면발의신 짜장라볶이\n"+"4980원에서 할인가 1980원"
            binding.infoTextView.text = "5시간전 | 조회 90 | 댓글 22 | 공감 2"
            binding.locationTextView.text="면목동"
            binding.iconImageView.setImageResource(R.drawable.profile_4)
            binding.detailImageView.setImageResource(R.drawable.fac4)

        }
        if (sequence == "sequence=310"){
            binding.reviewTextView.text = "청년1인가구 재무교육안내"
            binding.reviewContentTextView.text = "🔥신청기간: 2023. 9. 6까지\n"+"교육일시: 1차시 2023. 9. 12. 19:00 ~ 21:00\n2차시 2023. 9. 19. 19:00 ~ 21:00"+
                    "내용:\n1차시 - 평생월급 국민연금\n2차시 - 합리적인 경제생활과 금융사기예방\n대상: 서울 생활권 20~40대 청년1인가구(동대문구 생활권 우선지원)\n장소: 동대문구가족센터 4층 다목적강당\n문의: 동대문구1인가구지원센터 (070-1234-1234)"
            binding.infoTextView.text = "4일전 | 조회 680 | 댓글 14 | 공감 0"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_1)
            binding.detailImageView.setImageResource(R.drawable.info_1)

        }
        if (sequence == "sequence=311"){
            binding.reviewTextView.text = "동대문오랑 청년 1인가구 집을 고쳐준대요!"
            binding.reviewContentTextView.text = "동대문구에 거주하는 1인 가구 청년을 대상으로 동대문 오랑에서\n주거 개선 서비스를 제공한다고 해요!!\n1인가구 분들 꼭 챙기시길!\n좋은 정보 있어서 공유합니다~\n"+
                    "22,000원 정도는 부담해야 한다고 해요.\n그래도최고네요!\n신청 방법: 구글폼 작성, 홍보물 참고\n문의: 02.908.1234 매니저 김석주"
            binding.infoTextView.text = "4일전 | 조회 420 | 댓글 12 | 공감 0"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_2)
            binding.detailImageView.setImageResource(R.drawable.info_2)

        }
        if (sequence == "sequence=312"){
            binding.reviewTextView.text = "민방위"
            binding.reviewContentTextView.text = "오늘 훈련문자 왔어요 참고하세요"
            binding.infoTextView.text = "3시간전 | 조회 35 | 댓글 1 | 공감 5"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_3)
            binding.detailImageView.setImageResource(R.drawable.info_3)

        }
        if (sequence == "sequence=313"){
            binding.reviewTextView.text = "새로 오픈해서 50% 할인하네요~"
            binding.reviewContentTextView.text = "라미 스킨케어 첫방문 50% 할인이래요 저도 가보려고요 ~"
            binding.infoTextView.text = "4시간전 | 조회 50 | 댓글 2 | 공감 1"
            binding.locationTextView.text="장안동"
            binding.iconImageView.setImageResource(R.drawable.profile_4)
            binding.detailImageView.setImageResource(R.drawable.info_4)

        }
        if (sequence == "sequence=314"){
            binding.reviewTextView.text = "함께해요 동대문구 예술인들의 일상 문화 페스티벌"
            binding.reviewContentTextView.text = "10월 10일은 동대문구 예술인들의 공간 사랑채의 공연입니다.\n" +
                    "동대문구 주민은 50% 할인이라니 많은 관람 부탁드립니다."
            binding.infoTextView.text = "6시간전 | 조회 230 | 댓글 1 | 공감 6"
            binding.locationTextView.text="답십리동"
            binding.iconImageView.setImageResource(R.drawable.profile_5)
            binding.detailImageView.setImageResource(R.drawable.info_5)

        }
        if (sequence == "sequence=410"){
            binding.reviewTextView.text = "방탈출 좋아하는 2030 모여라"
            binding.reviewContentTextView.text = "방탈출 좋아하는데 같이 갈 사람 없는 동친들 댓글 달아주세요\n가기 전에 밥도 같이 먹고 연방해요 공포 테마 선호합니다"
            binding.infoTextView.text = "17분전 | 조회 6 | 댓글 1 | 공감 0"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_3)
            binding.detailImageView.setImageResource(R.drawable.toge1)

        }
        if (sequence == "sequence=411"){
            binding.reviewTextView.text = "중랑천 라이딩"
            binding.reviewContentTextView.text = "자전거 라이딩 모임 만들어 보아요\n" +
                    "mtb, 로드, 그래블, cx, 따릉이 등\n" +
                    "자전거 정보도 같이 공유해요~\n" +
                    "주로 라이딩 코스는 중랑천, 한강\n" +
                    "가끔은 100km 수준의 라이딩~"
            binding.infoTextView.text = "48분전 | 조회 38 | 댓글 4 | 공감 0"
            binding.locationTextView.text="회귀동"
            binding.iconImageView.setImageResource(R.drawable.profile_4)
            binding.detailImageView.setImageResource(R.drawable.toge2)

        }
        if (sequence == "sequence=412"){
            binding.reviewTextView.text = "혼자 살아서 16개를 다 사기엔 너무 많아요"
            binding.reviewContentTextView.text = "ㅠ_ㅠ 절반씩 나눠서 공동구매하실 분 어디 없을까요\n한빛마트 앞에서 거래해요"
            binding.infoTextView.text = "1시간전 | 조회 66 | 댓글 2 | 공감 0"
            binding.locationTextView.text="청량리동"
            binding.iconImageView.setImageResource(R.drawable.profile_5)
            binding.detailImageView.setImageResource(R.drawable.toge3)

        }
        if (sequence == "sequence=413"){
            binding.reviewTextView.text = "혹시 마이프로틴 같이 구매하실 분들 계신가요?"
            binding.reviewContentTextView.text = "7만원 이상 무료배송에,, 배송비가 2만원인지라\n공동구매할 분들 있으면 좋을 것 같아서요!\n\n제가 전동초 근처에 사는데 이 근방에 사시면 집앞까지 가져다 드릴게요!"
            binding.infoTextView.text = "1시간전 | 조회 38 | 댓글 1 | 공감 0"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_6)
            binding.detailImageView.setImageResource(R.drawable.toge4)

        }
        if (sequence == "sequence=414"){
            binding.reviewTextView.text = "<디퓨저 만들기> 원데이 클래스 같이 가실 분 구합니당"
            binding.reviewContentTextView.text = "지인의 지인 통해서 디퓨저 만들기 원데이 클래스 할인권을 받았는데\n혼자 가기 좀 그래서 같이 가실 분 구합니당!\n제가 20대라 20대면 좋겠어요!"+"\n" +
                    "날짜는 9월 4일이고 장소는 노원입니다!\n" +
                    "비용은 할인해서 5,000원입니다~!\n" +
                    "그럼 댓글 주세용"
            binding.infoTextView.text = "3시간전 | 조회 80 | 댓글 4 | 공감 2"
            binding.locationTextView.text="휘경동"
            binding.iconImageView.setImageResource(R.drawable.profile_1)
            binding.detailImageView.setImageResource(R.drawable.toge5)

        }
        if (sequence == "sequence=510"){
            binding.reviewTextView.text = "수박 한통 혼자 있을 때 절때 사지 마세요"
            binding.reviewContentTextView.text = "지난주일욜날  넘먹고싶어서 한통구매 하나로마트 라인에 있는 과일가게 존맛탱\n과일집에서(모든 과일이 비싸지만 후회절대없음..)\n"+
                    "사와서 너무 많을까봐 윗집 할매한테두 1/4 조각 드림..\n아직도 이모냥  껍질은 덤  담부턴 부조건 조각 살꺼임\n 혼자서시는분들은 조각 추천"
            binding.infoTextView.text = "9분전 | 조회 21 | 댓글 3 | 공감 4"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_1)
            binding.detailImageView.setImageResource(R.drawable.pass1)

        }
        if (sequence == "sequence=511"){
            binding.reviewTextView.text = "양조절 실패"
            binding.reviewContentTextView.text = "다들 자취하는데 음식 양조절 실패 해보셨죠?"+"전 6인분 만들어버림. ㅇ(-("
            binding.infoTextView.text = "12분전 | 조회 39 | 댓글 5 | 공감 6"
            binding.locationTextView.text="전농동"
            binding.iconImageView.setImageResource(R.drawable.profile_1)
            binding.detailImageView.setImageResource(R.drawable.pass2)

        }
        if (sequence == "sequence=512"){
            binding.reviewTextView.text = "첫자취생 종량제봉투"
            binding.reviewContentTextView.text = "안녕하세요! 제가 이번에 경남에서 올라와 처음 자취를 하게되었는데요\n사회초년생이기도하고 아무것도 몰라서 여쭤보려구용..\n"+
                    "혹시 종량제 봉투 묶음으로 구매하려면 조금이라도 저렴하게 살 수 있는 곳이 있나요?\n다 똑같은 가격이라면 그냥 편의점에서 구매하려구용..ㅎㅎ"
            binding.infoTextView.text = "28분전 | 조회 12 | 댓글 1 | 공감 0"
            binding.locationTextView.text="답십리동"
            binding.iconImageView.setImageResource(R.drawable.profile_3)
            binding.detailImageView.setImageResource(R.drawable.pass3)

        }
        if (sequence == "sequence=513"){
            binding.reviewTextView.text = "바퀴벌레 잡아주세요ㅠ"
            binding.reviewContentTextView.text = "시조사거리 쪽에 자취하는데 바퀴벌레 한마리 큰게 나와서 잡아줄 사람 찾아요 ㅠㅠㅠ\n"+
                    "사례금으로 (20000₩)드리겠습니다..잡아주시면 바로 입금해드리겠습니다\n"+"여성분만 연락 주세요 ㅠㅠ 제가 여자 혼자 자취하는거여서 ㅠㅠ\n"+"정말 제발 부탁드립니다 ㅜㅠㅠㅠㅠㅠ\n지갑이랑 옷이랑 다 안에 있어서 밖에 나가지도 못하고있어요\n"+
                    "처음에 발견 했을때 바로 없어졌었는데 다시 나와서 부탁 드립니다 .. ㅠㅠㅠㅠ"
            binding.infoTextView.text = "2시간전 | 조회 120 | 댓글 8 | 공감 0"
            binding.locationTextView.text="면목동"
            binding.iconImageView.setImageResource(R.drawable.profile_3)
            binding.detailImageView.setImageResource(R.drawable.pass4)

        }
        if (sequence == "sequence=514"){
            binding.reviewTextView.text = "벽걸이 에어컨 청소 업체"
            binding.reviewContentTextView.text = "자취생인데 겨울쯤 들어와서 전에 쓰던 에어컨을\n" +
                    "청소하려는데 가격이 얼마쯤히려요 ㅠㅠ? 업체 아시는분 계실까요"
            binding.infoTextView.text = "3시간전 | 조회 56 | 댓글 2 | 공감 0"
            binding.locationTextView.text="장안동"
            binding.iconImageView.setImageResource(R.drawable.profile_3)
            binding.detailImageView.setImageResource(R.drawable.pass5)

        }
        if (sequence == "sequence=610"){
            binding.reviewTextView.text = "강아지 혼자 돌아다녀요"
            binding.reviewContentTextView.text = "새벽 12시 50분쯤에 밝은 갈색 작은 강아지 혼자 돌아다니네요\n"+
                    "털 정리가 어느정도 된거같은데 혹시 몰라서 글 올립니다. 위치는 성당 근처에요"
            binding.infoTextView.text = "3시간전 | 조회 322 | 댓글 30 | 공감 15"
            binding.locationTextView.text="답십리동"
            binding.iconImageView.setImageResource(R.drawable.profile_5)
            binding.detailImageView.setImageResource(R.drawable.etc1)

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
