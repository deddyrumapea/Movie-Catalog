package com.romnan.moviecatalog.ui.discover

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.utils.DummyGenerator
import com.romnan.moviecatalog.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class DiscoverActivityTest {

    private val dummyPopularMovies = DummyGenerator.getPopularMovies()
    private val dummyPopularTvSeries = DummyGenerator.getPopularTvSeries()

    @Before
    fun setUp() {
        ActivityScenario.launch(DiscoverActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadPopularMovies() {
        onView(withId(R.id.rv_popular_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyPopularMovies.size
            )
        )
    }

    @Test
    fun loadPopularTvSeries() {
        onView(withText(R.string.tv_series)).perform(click())
        onView(withId(R.id.rv_popular_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_popular_tv_series)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyPopularTvSeries.size
            )
        )
    }

    @Test
    fun showAboutDialog() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.about)).perform(click())
        onView(withText(R.string.about_message)).check(matches(isDisplayed()))
        onView(withText(R.string.about_message)).check(matches(withText(R.string.about_message)))
    }
}