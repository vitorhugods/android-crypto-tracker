package xyz.schwaab.crypto.base.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

}