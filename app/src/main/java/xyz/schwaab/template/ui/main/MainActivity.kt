package xyz.schwaab.template.ui.main

import android.os.Bundle
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.replace
import xyz.schwaab.template.R
import xyz.schwaab.template.base.BaseActivity
import xyz.schwaab.template.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.includeAppbar.defaultToolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace<MainFragment>(R.id.container)
                .commitNow()
        }
        viewModel.mainTitle.observe(this, { title ->
            supportActionBar?.title = title
        })
    }
}
