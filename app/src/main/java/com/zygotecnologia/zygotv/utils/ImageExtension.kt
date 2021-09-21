package com.zygotecnologia.zygotv.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R

fun Context.loadImage(posterPath: String?, imageView: ImageView, contentDescription: String?) {
    Glide.with(this)
        .load(posterPath?.let { img -> ImageUrlBuilder.buildPosterUrl(img) })
        .apply(RequestOptions().apply {
            placeholder(R.drawable.image_placeholder)
            transforms(CenterInside(), RoundedCorners(16))
        })
        .into(imageView)
    imageView.contentDescription = contentDescription
}