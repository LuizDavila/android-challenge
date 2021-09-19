package com.zygotecnologia.zygotv.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.ui.main.favourites.BlankFragment
import com.zygotecnologia.zygotv.ui.main.moviesAndSeries.HomePlaceholderFragment

private val TAB_TITLES = arrayOf(
    R.string.movies,
    R.string.series,
    R.string.favorites

)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomePlaceholderFragment.newInstance(isMovie = false)
            1 -> HomePlaceholderFragment.newInstance(isMovie = true)
            else -> {
                return BlankFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}