package com.zygotecnologia.zygotv.utils


import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ViewCustomToastBinding


fun Context.loadImage(posterPath: String?, imageView: ImageView, contentDescription: String?) {
    Glide.with(this)
        .load(posterPath)
        .apply(RequestOptions().apply {
            placeholder(R.drawable.image_placeholder)
            transforms(CenterInside(), RoundedCorners(16))
        })
        .into(imageView)
    imageView.contentDescription = contentDescription
}

fun Context.genericToast(message: CharSequence, drawableId: Int = R.drawable.bg_green) {
    val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = ViewCustomToastBinding.inflate(inflater)

    layout.txtTitle.text = message
    layout.txtTitle.background = ContextCompat.getDrawable(this, drawableId)

    Toast(this).apply {
        setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 8)
        duration = Toast.LENGTH_LONG
        view = layout.root
        show()
    }
}

fun Context.spannableTitle() =
    SpannableString(resources.getString(R.string.app_name)).apply {
        setSpan(StyleSpan(R.font.roboto_bold), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    this@spannableTitle,
                    R.color.red
                )
            ), 4, 6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }


