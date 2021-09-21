package com.zygotecnologia.zygotv.ui.main

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var spannable: SpannableString

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        spannableTitle()
    }

    private fun spannableTitle() {
        spannable = SpannableString(resources.getString(R.string.app_name))
        spannable.run {
            setSpan(StyleSpan(R.font.roboto_bold), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.red
                    )
                ), 4, 6,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.title.text = spannable
    }

}