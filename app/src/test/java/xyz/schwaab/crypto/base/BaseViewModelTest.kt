package xyz.schwaab.crypto.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import xyz.schwaab.crypto.util.MainCoroutineRule

abstract class BaseViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
}