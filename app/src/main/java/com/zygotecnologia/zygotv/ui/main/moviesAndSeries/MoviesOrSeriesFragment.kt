package com.zygotecnologia.zygotv.ui.main.moviesAndSeries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zygotecnologia.zygotv.data.model.CarrouselMoviesOrSeriesUIModel
import com.zygotecnologia.zygotv.data.model.MoviesOrSeriesUIModel
import com.zygotecnologia.zygotv.databinding.FragmentMoviesOrSeriesBinding
import com.zygotecnologia.zygotv.ui.main.ClickListener
import com.zygotecnologia.zygotv.ui.main.MainFragmentDirections
import com.zygotecnologia.zygotv.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesOrSeriesFragment : Fragment(), ClickListener {

    private val viewModel: MoviesOrSeriesViewModel by viewModel()
    private val isMovie by lazy { requireArguments().getBoolean(IS_MOVIE) }
    private val adapter = MoviesOrSeriesAdapter(this)
    private var topId: Int? = null
    private lateinit var binding: FragmentMoviesOrSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviesOrSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMoviesOrSeriesAsync(isMovie)
        listener()
        observers()
    }

    private fun listener() {
        binding.bannerImage.setOnClickListener {
            topId?.let { topId -> onClickDetail(topId) }
        }
    }

    private fun observers() {
        viewModel.moviesOrSeries.observe(viewLifecycleOwner, {
            setupTop(it.topShowUIModel)
            setupAdapter(it.seriesListByGenderUIModel)
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            toggleShimmer(it)
        })

    }

    private fun setupTop(topShowUIModel: CarrouselMoviesOrSeriesUIModel?) {
        requireContext().loadImage(
            topShowUIModel?.backdropPath,
            binding.bannerImage, topShowUIModel?.name
        )
        binding.seriesName.text = topShowUIModel?.name
        topId = topShowUIModel?.id
    }

    private fun setupAdapter(seriesListByGenderUIModel: List<MoviesOrSeriesUIModel>?) {
        binding.recyclerContainer.adapter = adapter
        seriesListByGenderUIModel?.let { series ->
            adapter.moviesOrSeriesList = series
        }
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

    override fun onClickDetail(id: Int) {
        findNavController().navigate(
            MainFragmentDirections
                .actionMoviesOrSeriesFragmentToDetailFragment(id, isMovie)
        )
    }

    companion object {

        private const val IS_MOVIE = "is_movie"

        fun newInstance(isMovie: Boolean): MoviesOrSeriesFragment {
            return MoviesOrSeriesFragment().apply {
                arguments = bundleOf(IS_MOVIE to isMovie)
            }
        }
    }
}