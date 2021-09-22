package com.zygotecnologia.zygotv.data.util

import android.content.Context
import com.zygotecnologia.zygotv.data.R

object ImageHelper {

    private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    private const val TMDB_API_QUERY = "api_key"

    fun buildPosterUrl(posterPath: String?, context: Context): String {
        return POSTER_URL + posterPath + "?$TMDB_API_QUERY=" + context.getString(R.string.TMDB_API_KEY)
    }

    fun buildBackdropUrl(backdropPath: String?, context: Context): String {
        return BACKDROP_URL + backdropPath + "?$TMDB_API_QUERY=" + context.getString(R.string.TMDB_API_KEY)
    }
}