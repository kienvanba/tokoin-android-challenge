package vn.kien.tokoinchallenge.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
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

@BindingAdapter(
    value = ["loadImage", "placeholder", "centerCrop", "fitCenter", "centerInside", "animation"],
    requireAll = false)
fun ImageView.loadImage(url: String? = "", placeHolder: Drawable?,
                        centerCrop: Boolean = false, fitCenter: Boolean = false, centerInside: Boolean = false,
                        animation: Boolean = false) {

    val requestCreator = Picasso.get().load(url?.trim())
    requestCreator.networkPolicy(NetworkPolicy.OFFLINE)
    if (!animation) requestCreator.noFade()
    if (centerCrop) requestCreator.fit().centerCrop()
    if (fitCenter) requestCreator.fit()
    if (centerInside) requestCreator.centerInside()
    placeHolder?.also { requestCreator.placeholder(it) }
    requestCreator.into(this)
}