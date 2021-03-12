package vn.kien.tokoinchallenge.ui.fragment.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import vn.kien.tokoinchallenge.ui.fragment.news.NewsFragment
import vn.kien.tokoinchallenge.ui.fragment.news.NewsType
import java.lang.RuntimeException

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewsFragment.newInstance(NewsType.Headline)
            1 -> NewsFragment.newInstance(NewsType.Custom)
            else -> throw RuntimeException("Booking Pager Adapter -> UnImplemented Fragment at position[$position]")
        }
    }
}