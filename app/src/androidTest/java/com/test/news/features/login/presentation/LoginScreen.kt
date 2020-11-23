package com.test.news.features.login.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R

class LoginScreen : BaseScreen() {

    private val editTextUserNameField = withId(R.id.editTextUserName)
    private val editTextPasswordField = withId(R.id.editTextPassword)
    private val loginButton = withId(R.id.buttonLogin)


    companion object {
        private const val VALID_USER_NAME = "user1"
        private const val VALID_USER_PASSWORD = "password"
        private const val INVALID_USERNAME_ERROR_TEXT = "Wrong user name"
    }

    fun loginAsValidUser() {
        enterUserName(VALID_USER_NAME)
        enterPassword(VALID_USER_PASSWORD)
        submitLoginDetails()
    }

    fun loginAsInvalidUser() {
        enterUserName("blah")
        enterPassword("blah")
        submitLoginDetails()
        onView(editTextUserNameField).perform(click())
    }

    fun verifyFieldsAndButtonsArePresent() {
        onView(editTextUserNameField).check(matches(isDisplayed()))
        onView(editTextPasswordField).check(matches(isDisplayed()))
        onView(loginButton).check(matches(isDisplayed()))
    }

    fun hasWrongUserNameError() {
        onView(hasErrorText(INVALID_USERNAME_ERROR_TEXT)).check(matches(isDisplayed()))
    }

    private fun enterUserName(userName: String) {
        onView(editTextUserNameField).perform(clearText(), typeText(userName))
    }

    private fun enterPassword(password: String) {
        onView(editTextPasswordField).perform(clearText(), typeText(password))
    }

    private fun submitLoginDetails() {
        onView(loginButton).perform(click())
    }

}