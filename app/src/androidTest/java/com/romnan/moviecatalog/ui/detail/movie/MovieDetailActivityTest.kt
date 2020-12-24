package com.romnan.moviecatalog.ui.detail.movie

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.ui.discover.DiscoverActivity
import com.romnan.moviecatalog.utils.DummyGenerator
import com.romnan.moviecatalog.utils.EspressoIdlingResource
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailActivityTest {
    private val dummyMovieDetail = DummyGenerator.getMovieDetail()

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
    fun loadMovieDetails() {
        onView(withText(dummyMovieDetail.title)).perform(click())

        // Check views
        onView(withId(R.id.text_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_movie_title)).check(matches(withText(dummyMovieDetail.title)))

        onView(withId(R.id.text_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_user_score)).check(matches(withText(R.string.user_score)))

        onView(withId(R.id.text_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score)).check(matches(withText(dummyMovieDetail.voteAverage.toString())))

        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(withText("${dummyMovieDetail.runtime} minutes")))

        onView(withId(R.id.text_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tagline)).check(matches(withText(dummyMovieDetail.tagline)))
    }

    @Test
    fun addMovieToFavorite() {
        // Do it 10 times to add 10 movies to favorite
        for (i in 1..10) {
            onView(withId(R.id.rv_popular_movies)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(i, click())
            )
            onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
            onView(withId(R.id.btn_favorite)).check(matches(withText(R.string.favorite)))
            onView(withId(R.id.btn_favorite)).perform(click())
            onView(withId(R.id.btn_favorite)).check(matches(withText(R.string.favorited)))
            pressBack()
        }
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