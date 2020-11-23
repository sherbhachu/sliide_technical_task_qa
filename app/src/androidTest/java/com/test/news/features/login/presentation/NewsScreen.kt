package com.test.news.features.login.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf

class NewsScreen : BaseScreen() {

    fun newsScreenIsDisplayed() {
        repeatUntil {
            onView(allOf(withId(R.id.action_bar), hasDescendant(withText("News"))))
            .check(matches(isCompletelyDisplayed()))
        }
    }

    fun failedToLoadNewsMessageIsSeen() {
        waitForIdWithTextToBeCompletelyVisible(R.id.textViewError,"Failed to load news" )
    }

    fun hasNewsImagesVisible() {
        repeatUntil {
            onView(allOf(withId(R.id.recyclerViewNews), hasDescendant(withId(R.id.imageView))))
                .check(matches(isCompletelyDisplayed()))
        }
    }

    fun clickOnFirstNewsImageWhenVisible() {
        onView(allOf(withId(R.id.recyclerViewNews), hasDescendant(withId(R.id.imageView))))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
    }
}