package vn.kien.tokoinchallenge.ui.fragment.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_web_view.*
import org.koin.android.ext.android.inject
import vn.kien.tokoinchallenge.R
import vn.kien.tokoinchallenge.data.local.DataTransferHelper
import vn.kien.tokoinchallenge.model.News

class WebViewFragment : Fragment() {

    companion object {
        const val URL = "_url"
    }

    private val dataTransferHelper: DataTransferHelper by inject()
    private lateinit var url: String
    private var title: String = ""
        set(value) {
            field = value
            (activity as? AppCompatActivity)?.supportActionBar?.title = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            val news: News = dataTransferHelper.pop(it.getString(URL)!!)
            url = news.url
            title = news.source.name
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        web_view.loadUrl(url)
    }
}