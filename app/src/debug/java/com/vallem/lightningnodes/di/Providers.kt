package com.vallem.lightningnodes.di

import okhttp3.logging.HttpLoggingInterceptor

fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor? = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}