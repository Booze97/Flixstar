package com.example.flixstar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Headers
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler.JSON

class MainActivity : AppCompatActivity() {

    private val movies = mutableListOf<Movie>()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = MovieAdapter(movies)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

        client.get(url, object : JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                val movieArray = json.jsonObject.getJSONArray("results")

                for (i in 0 until movieArray.length()) {
                    val movieJson = movieArray.getJSONObject(i)
                    movies.add(
                        Movie(
                            movieJson.getString("title"),
                            movieJson.getString("overview"),
                            movieJson.getString("poster_path")
                        )
                    )
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String?, throwable: Throwable?) {
                throwable?.printStackTrace()
            }
        })
    }
}