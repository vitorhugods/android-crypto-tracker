package xyz.schwaab.crypto.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import xyz.schwaab.crypto.app.di.viewModels
import xyz.schwaab.crypto.base.BaseModules
import xyz.schwaab.crypto.ticker.TickerModules

class CryptoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CryptoApplication)
            @Suppress("EXPERIMENTAL_API_USAGE")
            fragmentFactory()
            modules(viewModels)
            modules(BaseModules.list)
            modules(TickerModules.list)
        }
    }
}