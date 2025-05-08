package com.vallem.lightningnodes.di

import com.vallem.lightningnodes.data.repository.NodeRepositoryImpl
import com.vallem.lightningnodes.domain.repository.NodeRepository
import org.koin.dsl.module

val RepositoryModule = module {
    factory<NodeRepository> {
        NodeRepositoryImpl(get())
    }
}