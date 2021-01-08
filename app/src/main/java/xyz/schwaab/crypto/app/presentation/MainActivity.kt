package xyz.schwaab.crypto.app.presentation

import android.os.Bundle
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.replace
import xyz.schwaab.crypto.R
import xyz.schwaab.crypto.base.BaseActivity
import xyz.schwaab.crypto.databinding.MainActivityBinding
import xyz.schwaab.crypto.ticker.presentation.CurrencyTickerFragment

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.includeAppbar.defaultToolbar)
        if (savedInstanceState == null) {
            @Suppress("EXPERIMENTAL_API_USAGE")
            supportFragmentManager.beginTransaction()
                .replace<CurrencyTickerFragment>(R.id.container)
                .commitNow()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.mainTitle.observe(this, { title ->
            supportActionBar?.title = title
        })
    }
}
