package com.romnan.moviecatalog.ui.catalog

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.util.DataDummy
import org.junit.Rule
import org.junit.Test

class CatalogActivityTest {

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityTestRule(CatalogActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_pop_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_pop_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_pop_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_pop_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun showAboutDialog() {
        onView(withId(R.id.menu_about)).perform(click())
        onView(withSubstring("Developed by")).check(matches(isDisplayed()))
        onView(withSubstring("Developed by")).check(matches(withText("Developed by Deddy Romnan Rumapea\nIcons made by Freepik from Flaticon")))
    }
}