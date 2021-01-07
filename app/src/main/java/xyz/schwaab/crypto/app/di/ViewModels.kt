package xyz.schwaab.crypto.app.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.schwaab.crypto.app.presentation.MainViewModel

val viewModels = module {
    viewModel { MainViewModel() }
}