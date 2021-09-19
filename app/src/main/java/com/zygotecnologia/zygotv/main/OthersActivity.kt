package com.zygotecnologia.zygotv.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class OthersActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO


    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.others_main)

//        launch(Dispatchers.IO) { loadShows() }

    }

//    private suspend fun loadShows() {
//        val genres =
//            tmdbApi
//                .fetchGenresAsync(TmdbApi.TMDB_API_KEY, "BR")
//                ?.genres
//                ?: emptyList()
//        val shows =
//            tmdbApi
//                .fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR")
//                ?.results
//                ?.map { show ->
//                    show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
//                }
//                ?: emptyList()
//
//
//        withContext(Dispatchers.Main) {
//            showList.adapter = MainAdapter(shows)
//        }
//    }
}