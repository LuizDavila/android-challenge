package com.zygotecnologia.zygotv.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zygotecnologia.zygotv.databinding.DetailFragmentBinding
import com.zygotecnologia.zygotv.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val viewModel: DetailViewModel by viewModel()

    private val detailId by lazy { requireArguments().getInt("id") }

    private val adapter = DetailSeriesAdapter()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchShowAsync("27490b1bf49c0e5ffaa07dfd947e9605", detailId)
        observer()
    }

    private fun observer() {
        viewModel.detailSeries.observe(viewLifecycleOwner, Observer {

            binding.seriesName.text = it.name
            requireContext().loadImage(
                it.posterPath, binding.bannerImage, it.name
            )
            binding.recyclerDetailSeries.adapter = adapter


        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}