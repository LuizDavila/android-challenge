package com.zygotecnologia.zygotv.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.data.model.SeasonUIModel
import com.zygotecnologia.zygotv.databinding.ItemSeasonDetailBinding
import com.zygotecnologia.zygotv.utils.loadImage

class DetailSeriesAdapter : RecyclerView.Adapter<DetailSeriesAdapter.ItemViewHolder>() {

    var detailList: List<SeasonUIModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = detailList.size


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(detailList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ItemSeasonDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class ItemViewHolder(private val binding: ItemSeasonDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SeasonUIModel) = item.run {
            binding.seasonTv.text = name
            binding.seasonDescriptionTv.text = overview
            binding.root.context.loadImage(posterPath, binding.seasonImage, name)


        }
    }
}