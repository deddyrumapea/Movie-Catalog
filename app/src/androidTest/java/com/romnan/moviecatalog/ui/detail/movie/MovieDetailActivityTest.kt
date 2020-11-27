package com.romnan.moviecatalog.ui.detail.movie

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.romnan.moviecatalog.R
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class MovieDetailActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()[0]

    @get:Rule
    var activityRule = ActivityTestRule(MovieDetailActivity::class.java, false, false)

    @Test
    fun loadMovieDetails() {
        // Create intent
        val movieId = dummyMovie.id
        val intent = Intent().putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movieId)
        activityRule.launchActivity(intent)

        // Check views
        onView(withId(R.id.text_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_movie_title)).check(matches(ViewMatchers.withText(dummyMovie.title)))
        onView(withId(R.id.text_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(ViewMatchers.withText(dummyMovie.overview)))
    }

    @Test
    fun loadMovieDetailsWithoutExtra() {
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