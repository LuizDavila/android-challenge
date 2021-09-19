package com.zygotecnologia.zygotv.data.model

data class SeriesUIModel(
    val topShowUIModel: ItemSeriesUIModel?,
    val seriesListByGenderUIModel: List<SeriesListByGenderUIModel>?
)

data class SeriesListByGenderUIModel(
    val nameGenre: String,
    val seriesList: List<ItemSeriesUIModel>
)

data class ItemSeriesUIModel(
    val name: String,
    val id: Int?,
    val posterPath: String
)
