package com.zygotecnologia.zygotv.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.model.MoviesOrSeriesDetailUIModel
import com.zygotecnologia.zygotv.data.model.SeasonUIModel
import com.zygotecnologia.zygotv.databinding.DetailFragmentBinding
import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import com.zygotecnologia.zygotv.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private lateinit var binding: DetailFragmentBinding
    private val viewModel: DetailViewModel by viewModel()

    private val detailId by lazy { requireArguments().getInt("id") }
    private val isMovie by lazy { requireArguments().getBoolean("movie") }

    private val adapter = DetailSeriesAdapter()

    private lateinit var favoriteDTO: FavoriteDTO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchShowAsync(isMovie, detailId)
        observer()
        listener()
    }

    private fun listener() {
        binding.addFavorite.setOnClickListener {
            viewModel.insert(favoriteDTO)
        }
    }

    private fun observer() {
        viewModel.detailSeries.observe(viewLifecycleOwner, {
            favoriteDTO = FavoriteDTO(it.id ?: 0, it.name, it.posterPath)
            setupTop(it)
            setupAdapter(it.season)
        })

        viewModel.insert.observe(viewLifecycleOwner,{
            Toast.makeText(context, getString(R.string.add_favotite), Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            toggleShimmer(it)
        })
    }

    private fun setupTop(moviesOrSeriesDetailUIModel: MoviesOrSeriesDetailUIModel) {
        binding.seriesName.text = moviesOrSeriesDetailUIModel.name
        requireContext().loadImage(
            moviesOrSeriesDetailUIModel.backdropPath, binding.bannerImage, moviesOrSeriesDetailUIModel.name
        )
    }

    private fun setupAdapter(season: List<SeasonUIModel>) {
        binding.recyclerDetailSeries.adapter = adapter
        adapter.detailList = season
    }

    private fun toggleShimmer(isVisible: Boolean) {
        if (isVisible) {
            binding.shimmerLayoutBanner.startShimmer()
            binding.shimmerLayoutSeason.startShimmer()
        } else {
            binding.shimmerLayoutBanner.stopShimmer()
            binding.shimmerLayoutSeason.stopShimmer()
        }
        binding.shimmerLayoutBanner.isVisible = isVisible
        binding.shimmerLayoutSeason.isVisible = isVisible
    }
}