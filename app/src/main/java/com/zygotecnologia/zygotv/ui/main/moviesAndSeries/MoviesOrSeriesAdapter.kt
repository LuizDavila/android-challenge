package com.zygotecnologia.zygotv.ui.main.moviesAndSeries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.data.model.MoviesOrSeriesUIModel
import com.zygotecnologia.zygotv.databinding.ItemMoviesOrSeriesGenreBinding
import com.zygotecnologia.zygotv.ui.main.ClickListener

class MoviesOrSeriesAdapter(private val listener: ClickListener) : RecyclerView.Adapter<MoviesOrSeriesAdapter.ItemViewHolder>() {

    var moviesOrSeriesList: List<MoviesOrSeriesUIModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount() = moviesOrSeriesList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(moviesOrSeriesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
            ItemMoviesOrSeriesGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    inner class ItemViewHolder(private val binding: ItemMoviesOrSeriesGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoviesOrSeriesUIModel) = item.run {
            binding.genre.text = nameGenre
            val adapter =  CarrouselMovieOrSeriesAdapter(listener)
            adapter.carrouselMovieOrSeriesList = seriesList
            binding.recyclerSeries.adapter = adapter
        }
    }


}