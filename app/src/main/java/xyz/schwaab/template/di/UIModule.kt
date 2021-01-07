package xyz.schwaab.template.di

import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module
import xyz.schwaab.template.ui.main.MainFragment

val uiModule = module {
    fragment { MainFragment() }
}