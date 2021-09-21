import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.ui.main.favorites.FavoritesFragment
import com.zygotecnologia.zygotv.ui.main.moviesAndSeries.HomePlaceholderFragment

val TAB_TITLES = arrayOf(
    R.string.movies,
    R.string.series,
    R.string.favorites

)

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomePlaceholderFragment.newInstance(isMovie = true)
            1 -> HomePlaceholderFragment.newInstance(isMovie = false)
            else -> {
                return FavoritesFragment()
            }
        }
    }

    override fun getItemCount() = 3
}