package com.vallem.lightningnodes.di

import com.vallem.lightningnodes.presentation.screen.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { HomeViewModel(get()) }
}