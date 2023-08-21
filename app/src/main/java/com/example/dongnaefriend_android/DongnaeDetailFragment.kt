import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dongnaefriend_android.R
import model.Post

class DongnaeDetailFragment : Fragment() {
    private var post: Post? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dongnae_detail, container, false)
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
}
