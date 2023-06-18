package com.gmribas.desafioverity

import android.app.Application
import com.gmribas.desafioverity.domain.di.useCaseModule
import com.gmribas.desafioverity.network.di.networkModule
import com.gmribas.desafioverity.repository.di.repositoryMapperModule
import com.gmribas.desafioverity.repository.di.repositoryModule
import com.gmribas.desafioverity.repository.di.sourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            val modules = listOf(
                networkModule,
                sourceModule,
                repositoryMapperModule,
                repositoryModule,
                useCaseModule
            )

            modules(modules)
            androidContext(this@MainApplication)
            androidLogger(Level.INFO)
        }
    }
}