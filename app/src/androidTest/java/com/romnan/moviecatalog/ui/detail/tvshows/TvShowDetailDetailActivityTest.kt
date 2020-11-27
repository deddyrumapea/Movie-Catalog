package com.romnan.moviecatalog.ui.detail.tvshows

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

class TvShowDetailDetailActivityTest {
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]

    @get:Rule
    var activityRule = ActivityTestRule(TvShowDetailActivity::class.java, false, false)

    @Test
    fun loadTvShowDetails() {
        // Create intent
        val tvShowId = dummyTvShow.id
        val intent = Intent().putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_ID, tvShowId)
        activityRule.launchActivity(intent)

        // Check views
        onView(withId(R.id.text_tvshow_title)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_tvshow_title)).check(matches(ViewMatchers.withText(dummyTvShow.title)))
        onView(withId(R.id.text_overview)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.text_overview)).check(matches(ViewMatchers.withText(dummyTvShow.overview)))
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