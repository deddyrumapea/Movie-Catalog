package com.romnan.moviecatalog.ui.detail.tvseries

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.ui.detail.movie.MovieDetailActivity
import com.romnan.moviecatalog.ui.discover.DiscoverActivity
import com.romnan.moviecatalog.utils.DummyGenerator
import com.romnan.moviecatalog.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvSeriesDetailActivityTest {
    private val dummyTvSeries = DummyGenerator.getTvSeriesDetail()

    @get:Rule
    var activityRule = ActivityTestRule(MovieDetailActivity::class.java, false, false)

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
    fun loadTvShowDetails() {
        onView(withText(R.string.tv_series)).perform(click())
        onView(withText(dummyTvSeries.name)).perform(click())

        // Check views
        onView(withId(R.id.text_tv_series_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tv_series_title)).check(matches(withText(dummyTvSeries.name)))

        onView(withId(R.id.text_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_user_score)).check(matches(withText(R.string.user_score)))

        onView(withId(R.id.text_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score)).check(matches(withText(dummyTvSeries.voteAverage.toString())))

        onView(withId(R.id.text_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tagline)).check(matches(withText(dummyTvSeries.tagline)))
    }

    @Test
    fun loadTvShowDetailsWithoutExtra() {
        // Create intent without extra
        val intent = Intent()
        activityRule.launchActivity(intent)

        // Check if error message displayed
        onView(withId(R.id.image_error_icon)).check(matches(isDisplayed()))
        onView(withId(R.id.text_error_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_error_message)).check(matches(isDisplayed()))
        onView(withId(R.id.button_go_back)).check(matches(isDisplayed()))

        // Check "Go Back" button functionality
        onView(withId(R.id.button_go_back)).perform(click())
        assertTrue(activityRule.activity.isFinishing)
    }
}