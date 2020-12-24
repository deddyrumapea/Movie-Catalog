package com.romnan.moviecatalog.ui.favorite

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.ui.util.ScrollToBottomAction
import org.junit.Before
import org.junit.Test

class FavoriteActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(FavoriteActivity::class.java)
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movies)).perform(ScrollToBottomAction())
    }

    @Test
    fun loadFavoriteTvSeries() {
        onView(withText(R.string.tv_series)).perform(click())
        onView(withId(R.id.rv_favorite_tv_series)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_series)).perform(ScrollToBottomAction())
    }
}