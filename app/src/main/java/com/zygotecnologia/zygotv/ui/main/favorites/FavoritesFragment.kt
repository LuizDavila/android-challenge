package com.zygotecnologia.zygotv.ui.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.zygotecnologia.zygotv.databinding.FavoritesFragmentBinding
import com.zygotecnologia.zygotv.ui.main.moviesAndSeries.HomePlaceholderFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModel()
    private var _binding: FavoritesFragmentBinding? = null

    private val adapter = FavoritesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        layoutManager.flexWrap = FlexWrap.WRAP
        binding.favoriteRecycler.layoutManager = layoutManager
        viewModel.getAll()
        observers()
    }

    private fun  observers(){
        viewModel.getAll.observe(viewLifecycleOwner,{
            binding.favoriteRecycler.adapter = adapter

            adapter.favoritesList = it
        })
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