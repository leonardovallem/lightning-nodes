package com.vallem.lightningnodes.di

import com.vallem.lightningnodes.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val NetworkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get<Json>().asConverterFactory(MediaType.get(JSON_MEDIA_TYPE)))
            .build()
    }
}

private const val JSON_MEDIA_TYPE = "application/json; charset=UTF8"