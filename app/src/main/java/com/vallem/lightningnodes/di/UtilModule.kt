package com.vallem.lightningnodes.di

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val UtilModule = module {
    factory {
        Json {
            ignoreUnknownKeys = true
        }
    }
}