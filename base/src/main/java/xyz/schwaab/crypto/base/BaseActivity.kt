package xyz.schwaab.crypto.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        @Suppress("EXPERIMENTAL_API_USAGE")
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
    }
}
