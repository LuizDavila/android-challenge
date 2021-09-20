package com.zygotecnologia.zygotv.ui.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zygotecnologia.zygotv.databinding.FavoritesFragmentBinding
import com.zygotecnologia.zygotv.ui.main.moviesAndSeries.HomePlaceholderFragment

class FavoritesFragment : Fragment() {

    private val binding get() = _binding!!

    private lateinit var viewViewModel: FavoritesViewModel
    private var _binding: FavoritesFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): HomePlaceholderFragment {
            return HomePlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }


}