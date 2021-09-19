package com.zygotecnologia.zygotv.ui.main.moviesAndSeries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.model.ItemSeriesUIModel
import com.zygotecnologia.zygotv.databinding.ItemSeriesDetailBinding
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ItemSeriesAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<ItemSeriesAdapter.ItemViewHolder>() {

    var seriesList: List<ItemSeriesUIModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = seriesList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(seriesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            ItemSeriesDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class ItemViewHolder(private val binding: ItemSeriesDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemSeriesUIModel) = item.run {
            binding.seriesName.text = name
            Glide.with(binding.root.context)
                .load(posterPath.let { img -> ImageUrlBuilder.buildPosterUrl(img) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(binding.seriesImage)
            binding.seriesImage.setOnClickListener {
                if (id != null) {
                    listener.onClickDetail(id)
                }
            }
        }
    }
}

