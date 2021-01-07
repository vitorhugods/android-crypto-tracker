package xyz.schwaab.template

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import xyz.schwaab.template.di.servicesModule
import xyz.schwaab.template.di.uiModule
import xyz.schwaab.template.di.viewModelModule

class TemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TemplateApplication)
            fragmentFactory()
            modules(servicesModule, viewModelModule, uiModule)
        }
    }
}