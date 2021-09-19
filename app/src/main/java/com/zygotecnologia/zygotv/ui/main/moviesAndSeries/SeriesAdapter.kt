package com.zygotecnologia.zygotv.ui.main.moviesAndSeries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.data.model.SeriesListByGenderUIModel
import com.zygotecnologia.zygotv.databinding.ItemSeriesGenreBinding

class SeriesAdapter(private val listener: ClickListener) : RecyclerView.Adapter<SeriesAdapter.ItemViewHolder>() {

    var genreList: List<SeriesListByGenderUIModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount() = genreList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(genreList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
            ItemSeriesGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    inner class ItemViewHolder(private val binding: ItemSeriesGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SeriesListByGenderUIModel) = item.run {
            binding.genre.text = nameGenre
            val adapter =  ItemSeriesAdapter(listener)
            adapter.seriesList = seriesList
            binding.recyclerSeries.adapter = adapter
        }
    }


}