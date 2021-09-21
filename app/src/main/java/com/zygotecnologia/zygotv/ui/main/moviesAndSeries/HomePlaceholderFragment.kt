package com.zygotecnologia.zygotv.ui.main.moviesAndSeries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.zygotecnologia.zygotv.databinding.FragmentHomeBinding
import com.zygotecnologia.zygotv.ui.main.MainFragmentDirections
import com.zygotecnologia.zygotv.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomePlaceholderFragment : Fragment(), ClickListener {

    private val viewModel: MovieOrSeriesViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val isMovie by lazy { requireArguments().getBoolean(IS_MOVIE) }

    private val adapter = SeriesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMoviesOrSeriesAsync(isMovie)
        observers()
    }

    private fun observers() {
        viewModel.moviesOrSeries.observe(viewLifecycleOwner, Observer { it ->


            binding.seriesName.text = it.topShowUIModel?.name

            requireContext().loadImage(
                it.topShowUIModel?.posterPath,
                binding.bannerImage,
                it.topShowUIModel?.name

            )
            binding.bannerImage.setOnClickListener { it2 ->
                it.topShowUIModel?.id?.let { it1 -> onClickDetail(it1) }
            }

            binding.recyclerContainer.adapter = adapter

            it.seriesListByGenderUIModel?.let { series ->
                adapter.genreList = series
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            toggleShimmer(it)
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun toggleShimmer(isVisible: Boolean) {
        if (isVisible) {
            binding.shimmerLayoutBanner.startShimmer()
            binding.shimmerLayoutSeries.startShimmer()
        } else {
            binding.shimmerLayoutBanner.stopShimmer()
            binding.shimmerLayoutSeries.stopShimmer()
        }
        binding.shimmerLayoutBanner.isVisible = isVisible
        binding.shimmerLayoutSeries.isVisible = isVisible
    }


    companion object {

        private const val IS_MOVIE = "is_movie"

        fun newInstance(isMovie: Boolean): HomePlaceholderFragment {
            return HomePlaceholderFragment().apply {
                arguments = bundleOf(IS_MOVIE to isMovie)
            }
        }
    }

    override fun onClickDetail(id: Int) {
        findNavController().navigate(
            MainFragmentDirections
                .actionHomePlaceholderFragmentToDetailFragment(id)
        )
    }
}