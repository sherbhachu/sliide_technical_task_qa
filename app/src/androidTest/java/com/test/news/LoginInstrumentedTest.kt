package com.test.news

import androidx.test.rule.ActivityTestRule
import junit.framework.Assert.assertTrue
import com.test.news.features.login.presentation.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginInstrumentedTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    var loginScreen = LoginScreen()
    var newsScreen = NewsScreen()
    var utils = Utils()

    @Before
    fun wifiAndDataIsEnabled() {
        utils.enableWifiAndData()
    }

    @Test
    fun loggedOutUserOpensAppForFirstTime() {
        loginScreen.verifyFieldsAndButtonsArePresent()
    }

    @Test
    fun userLoginFails() {
        loginScreen.loginAsInvalidUser()
        loginScreen.hasWrongUserNameError()
    }

    @Test
    fun userLoginSucceeds() {
        loginScreen.loginAsValidUser()
        loginScreen.repeatUntil { (activityTestRule.activity.isFinishing) }
        newsScreen.newsScreenIsDisplayed()
    }

    @Test
    fun previouslyLoggedInUserIsTakenToNewsScreenWhenAppIsRelaunched() {
        loginScreen.loginAsValidUser()
        loginScreen.repeatUntil { (activityTestRule.activity.isFinishing) }
        newsScreen.newsScreenIsDisplayed()
        utils.sendAppToBackgroundThenResume()
        newsScreen.newsScreenIsDisplayed()
    }
}
