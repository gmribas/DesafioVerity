package com.gmribas.desafioverity.network.di

import com.gmribas.desafioverity.BuildConfig
import com.gmribas.desafioverity.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkhttp())
            .build()
            .create(ApiService::class.java)
    }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun provideOkhttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(provideLoggingInterceptor())
        .callTimeout(CALL_TIMEOUT_LIMIT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_LIMIT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_LIMIT, TimeUnit.SECONDS)
        .build()
}

const val CALL_TIMEOUT_LIMIT = 30L
const val READ_TIMEOUT_LIMIT = 30L
const val WRITE_TIMEOUT_LIMIT = 30L