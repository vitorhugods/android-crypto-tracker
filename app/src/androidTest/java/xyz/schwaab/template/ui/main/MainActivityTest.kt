package xyz.schwaab.template.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.amshove.kluent.mock
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import xyz.schwaab.template.R

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, true, false)

    lateinit var mockVm: MainViewModel

    private val titleLiveData = MutableLiveData<String>()

    @Before
    fun setup() {
        mockVm = mock(MainViewModel::class)

        loadKoinModules(module {
            viewModel { mockVm }
            fragment { MainFragment() }
        })
    }

    @After
    fun cleanUp() {
        stopKoin()
    }

    @Test
    fun shouldDisplayAppTitle() {
        val title = "Lorem Ipsum"
        titleLiveData.postValue(title)
        onView(withId(R.id.default_toolbar)).check(matches(withText(title)))
    }
}