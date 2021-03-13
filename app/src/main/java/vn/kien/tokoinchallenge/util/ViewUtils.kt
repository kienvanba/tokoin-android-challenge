package vn.kien.tokoinchallenge.util

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

fun RecyclerView.setupLayout(orientation: Int, divider: Boolean = true) {
    val layoutManager = LinearLayoutManager(context)
    layoutManager.orientation = orientation
    this.layoutManager = layoutManager
    if (divider) {
        addItemDecoration(
            DividerItemDecoration(context, orientation)
        )
    }
}

@BindingAdapter("onScrollListener")
fun RecyclerView.customScrollListener(listener: RecyclerView.OnScrollListener?) {
    if (listener != null) addOnScrollListener(listener)
}

@BindingAdapter("onRefreshListener")
fun SwipeRefreshLayout.customRefreshListener(listener: SwipeRefreshLayout.OnRefreshListener?) {
    if (listener != null) setOnRefreshListener(listener)
}

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.customRefreshing(refreshing: Boolean?) {
    isRefreshing = refreshing == true
}

@BindingAdapter("html")
fun TextView.setHtml(html: String = "") {
    text = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        Html.fromHtml(html)
    } else {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    }
}

@BindingAdapter(
    value = ["loadImage", "placeholder", "centerCrop", "fitCenter", "centerInside", "animation", "completion"],
    requireAll = false)
fun ImageView.loadImage(url: String? = "", placeHolder: Drawable?,
                        centerCrop: Boolean = false, fitCenter: Boolean = false, centerInside: Boolean = false,
                        animation: Boolean = false, completion: ((Boolean) -> Unit)? = null) {

    val requestCreator = Picasso.get().load(url?.trim())
    requestCreator.networkPolicy(NetworkPolicy.OFFLINE)
    if (!animation) requestCreator.noFade()
    if (centerCrop) requestCreator.fit().centerCrop()
    if (fitCenter) requestCreator.fit()
    if (centerInside) requestCreator.centerInside()
    placeHolder?.also { requestCreator.placeholder(it) }
    requestCreator.into(this, object : Callback {
        override fun onSuccess() { completion?.invoke(true) }

        override fun onError(e: Exception?) {
            val req = Picasso.get().load(url)
            if (!animation) req.noFade()
            if (centerCrop) req.fit().centerCrop()
            if (fitCenter) req.fit()
            if (centerInside) req.centerInside()
            placeHolder?.also { req.placeholder(it) }
            req.into(this@loadImage, object : Callback {
                override fun onSuccess() { completion?.invoke(true) }

                override fun onError(e: Exception?) { completion?.invoke(false) }
            })
        }
    })
}