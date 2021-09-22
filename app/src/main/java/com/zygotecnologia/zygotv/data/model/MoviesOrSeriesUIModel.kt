package com.zygotecnologia.zygotv.data.model

data class MoviesOrSeriesByGenderUIModel(
    val topShowUIModel: CarrouselMoviesOrSeriesUIModel?,
    val seriesListByGenderUIModel: List<MoviesOrSeriesUIModel>?
)

data class MoviesOrSeriesUIModel(
    val nameGenre: String,
    val seriesList: List<CarrouselMoviesOrSeriesUIModel>
)

data class CarrouselMoviesOrSeriesUIModel(
    val name: String,
    val id: Int?,
    val backdropPath: String
)
