package com.zygotecnologia.zygotv.ui.main.moviesAndSeries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.data.model.CarrouselMoviesOrSeriesUIModel
import com.zygotecnologia.zygotv.databinding.ItemCarrouselMoviesOrSeriesDetailBinding
import com.zygotecnologia.zygotv.ui.main.ClickListener
import com.zygotecnologia.zygotv.utils.loadImage

class CarrouselMovieOrSeriesAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<CarrouselMovieOrSeriesAdapter.ItemViewHolder>() {

    var carrouselMovieOrSeriesList: List<CarrouselMoviesOrSeriesUIModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = carrouselMovieOrSeriesList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(carrouselMovieOrSeriesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            ItemCarrouselMoviesOrSeriesDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class ItemViewHolder(private val binding: ItemCarrouselMoviesOrSeriesDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CarrouselMoviesOrSeriesUIModel) = item.run {
            binding.seriesName.text = name
            binding.root.context.loadImage(
                backdropPath, binding.seriesImage, name
            )

            binding.root.setOnClickListener {
                id?.let { id -> listener.onClickDetail(id) }
            }
        }
    }
}

