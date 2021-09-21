package com.zygotecnologia.zygotv.ui.main.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.data.model.FavoritesUIModel
import com.zygotecnologia.zygotv.databinding.ItemSeriesFavoriteBinding
import com.zygotecnologia.zygotv.utils.loadImage

class FavoritesAdapter() : RecyclerView.Adapter<FavoritesAdapter.ItemViewHolder>() {

    var favoritesList: List<FavoritesUIModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = favoritesList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(favoritesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            ItemSeriesFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class ItemViewHolder(private val binding: ItemSeriesFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoritesUIModel) = item.run {
            binding.root.context.loadImage(posterPath, binding.seriesImage, name)
        }
    }
}
