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
import com.example.dongnaefriend_android.databinding.FragmentDongnaeDetailBinding
import model.Post

class DongnaeDetailFragment : Fragment() {
    private var post: Post? = null
    private lateinit var binding : FragmentDongnaeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            post = it.getSerializable(ARG_POST) as? Post
            // TODO: 필요한 경우 post 객체를 여기에서 사용하거나 다른 메서드에서 사용할 수 있습니다.
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDongnaeDetailBinding.inflate(layoutInflater)


        var postArray = post.toString().split("Post(",", ",")")
        var sequence = postArray[1]


        if (sequence == "sequence=110"){
            binding.reviewTextView.text = "구 바우하우스 주변 24시 식당"
            binding.reviewContentTextView.text = "알바 퇴근이 늦은데 집가서 요리해먹기 너무 귀찮네요.. \n" +
                    "구 바우하우스 근처에 24시 식당 괜찮은 곳 추천해주실 수 있나요?국밥은 너무 질려서 ㅠㅠ 기사식당 같은 곳도 괜찮아용!!"
            binding.infoTextView.text = "4분전 | 조회 22 | 공감 장안동"
            binding.detailImageView.setImageResource(R.drawable.restaurant_1)

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
