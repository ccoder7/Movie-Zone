package com.omni.movieappliation.features.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.omni.movieappliation.R
import com.omni.movieappliation.entities.MovieEntity
import com.omni.movieappliation.features.home.EXTRA_MOVIE
import com.omni.movieappliation.useCases.IMAGE_SIZE
import com.omni.movieappliation.useCases.getImageURL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    val detailsViewModel by lazy { ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar_details)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar_details.setNavigationOnClickListener{finish()}
        val intent = intent
        if (intent.hasExtra(EXTRA_MOVIE))
            {
               val movie = intent?.getParcelableExtra<MovieEntity>(EXTRA_MOVIE)
                detailsViewModel.bind(movie!!)

            }
        bindViews()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
    private fun DetailsActivity.bindViews() = kotlin.with(detailsViewModel) {

        titleLiveData.observe(this@bindViews,
            Observer {
                toolbar_details.title = it
                toolbar_layout.title = it
            })
        titleLiveData.observe(this@bindViews,
            Observer {
                toolbar_details.title = it
                toolbar_layout.title = it
            })
        releaseDateLiveData.observe(this@bindViews, Observer {
            release_date.text = "Release Date: $it"
        })
        voteAverageDateLiveData.observe(this@bindViews, Observer {
            vote_avg.text = "Vote Avgerage: $it"
        })
        overViewLiveData.observe(this@bindViews, Observer {
            overView.text = it
        })
        posterPathLiveData.observe(this@bindViews, Observer {
            Picasso.get()
                .load(getImageURL(it, IMAGE_SIZE))
                .into(detail_img_movie)
            Log.d("callable ", getImageURL(it))
        })

        backDropLiveData.observe(this@bindViews, Observer {

            Picasso.get()
                .load(getImageURL(it))
                .into(detail_img_cover)
        })

    }

