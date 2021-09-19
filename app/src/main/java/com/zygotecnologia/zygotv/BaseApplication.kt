package com.zygotecnologia.zygotv

import android.app.Application
import com.zygotecnologia.zygotv.data.di.dataModule
import com.zygotecnologia.zygotv.data.di.remoteModule
import com.zygotecnologia.zygotv.data.di.repositoryModule
import com.zygotecnologia.zygotv.di.appModule
import com.zygotecnologia.zygotv.domain.di.useCaseModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

@FlowPreview
@ExperimentalCoroutinesApi
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.ERROR)
            modules(
                dataModule + repositoryModule + remoteModule + useCaseModule
                        + appModule
            )
        }
    }
}



