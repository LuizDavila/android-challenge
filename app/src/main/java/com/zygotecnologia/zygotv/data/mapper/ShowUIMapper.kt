package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.ItemSeriesUIModel
import com.zygotecnologia.zygotv.data.model.SeriesListByGenderUIModel
import com.zygotecnologia.zygotv.data.model.SeriesUIModel
import com.zygotecnologia.zygotv.domain.entity.response.GenreResponse
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse

object ShowUIMapper {

    fun map(list: List<ShowResponse>) = SeriesUIModel(
        getTopShow(list),
        mapSeriesListByGender(list)
    )

    private fun mapSeriesListByGender(list: List<ShowResponse>) =
        mapSeriesOrMovies(list).map {
            SeriesListByGenderUIModel(
                mapNameGenres(it.key),
                it.value.map { item -> mapItemSeries(item) }
            )
        }

    private fun getTopShow(list: List<ShowResponse>) =
        list.maxByOrNull { it.voteCount ?: 0 }?.run {
            mapItemSeries(this)
        }

    private fun mapItemSeries(showResponse: ShowResponse) =  showResponse.run {
        ItemSeriesUIModel(name ?: "", id, posterPath ?: "")
    }


    private fun mapSeriesOrMovies(list: List<ShowResponse>) =
        list.groupBy { it.genres }.entries.distinct()

    private fun mapNameGenres(namesGenre: List<GenreResponse>?): String {
        val nameBuilder = StringBuilder()
        namesGenre?.forEach {
            nameBuilder.append("${it.name}, ")
        }
        return nameBuilder.toString()
    }
}