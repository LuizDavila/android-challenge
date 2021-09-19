package com.zygotecnologia.zygotv.data.mapper

import com.zygotecnologia.zygotv.data.model.ItemSeriesUIModel
import com.zygotecnologia.zygotv.data.model.SeriesListByGenderUIModel
import com.zygotecnologia.zygotv.data.model.SeriesUIModel
import com.zygotecnologia.zygotv.domain.entity.response.ShowResponse

object ShowUIMapper {

    fun map(list: List<ShowResponse>) = SeriesUIModel(
        getTopShow(list),
        listOf(mapSeriesListByGender(list))
    )

    private fun mapSeriesListByGender(list: List<ShowResponse>) =
        SeriesListByGenderUIModel(
           "Comédia",
            list.map {  mapItemSeries(it) }
        )

    private fun getTopShow(list: List<ShowResponse>) =
        list.maxByOrNull { it.voteCount ?: 0 }?.run {
            mapItemSeries(this)
        }

    private fun mapItemSeries(showResponse: ShowResponse) =  showResponse.run {
        ItemSeriesUIModel(name ?: "", id, posterPath ?: "")
    }
}