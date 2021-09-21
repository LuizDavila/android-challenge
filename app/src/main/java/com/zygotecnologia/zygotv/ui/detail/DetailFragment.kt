package com.zygotecnologia.zygotv.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.zygotecnologia.zygotv.data.remote.TmdbApi.Companion.TMDB_API_KEY
import com.zygotecnologia.zygotv.databinding.DetailFragmentBinding
import com.zygotecnologia.zygotv.domain.entity.local.FavoriteDTO
import com.zygotecnologia.zygotv.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val viewModel: DetailViewModel by viewModel()

    private val detailId by lazy { requireArguments().getInt("id") }

    private val adapter = DetailSeriesAdapter()

    private lateinit var favoriteDTO: FavoriteDTO

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchShowAsync(TMDB_API_KEY, detailId)
        observer()
    }

    private fun observer() {
        viewModel.detailSeries.observe(viewLifecycleOwner, {

            binding.seriesName.text = it.name
            requireContext().loadImage(
                it.posterPath, binding.bannerImage, it.name
            )
            binding.recyclerDetailSeries.adapter = adapter

            adapter.detailList = it.season
            favoriteDTO = FavoriteDTO(it.id ?: 0, it.name, it.posterPath)

        })

            binding.addFavorite.setOnClickListener {
                viewModel.insert(favoriteDTO)
            }


        viewModel.loading.observe(viewLifecycleOwner, {
            toggleShimmer(it)
        })
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}