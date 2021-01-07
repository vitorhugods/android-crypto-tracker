package xyz.schwaab.template.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import xyz.schwaab.template.util.MainCoroutineRule

abstract class BaseViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
}