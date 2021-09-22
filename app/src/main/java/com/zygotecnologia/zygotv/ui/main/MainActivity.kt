package com.zygotecnologia.zygotv.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.monitor.DISCONNECTED
import com.zygotecnologia.zygotv.monitor.NetWorkManger
import com.zygotecnologia.zygotv.utils.genericToast
import com.zygotecnologia.zygotv.utils.spannableTitle

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.title.text = spannableTitle()
        NetWorkManger.networkStatus.observe(this, {
                when (it) {
                    DISCONNECTED -> genericToast(getString(R.string.network_error))
                }
            })

    }

}