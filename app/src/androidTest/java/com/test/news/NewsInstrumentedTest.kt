package com.test.news

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.features.login.presentation.LoginScreen
import com.test.news.features.login.presentation.NewsScreen
import com.test.news.features.login.presentation.Utils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NewsInstrumentedTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(
        LoginActivity::class.java)

    //var newsScreenTestRule = ActivityTestRule<NewsActivity>(
    //    NewsActivity::class.java)

    var loginScreen = LoginScreen()
    var newsScreen = NewsScreen()
    var utils = Utils()

    @Before
    fun userLoginSucceeds() {
        utils.enableWifiAndData()
        loginScreen.loginAsValidUser()
    }

    @After
    fun enableWifi() {
        utils.enableWifiAndData()
    }

    @Test
    fun newsImagesAreLoadedForLoggedInUser() {
        //newsScreen.repeatUntil { activityTestRule.activity.isFinishing }
        newsScreen.newsScreenIsDisplayed()
        newsScreen.hasNewsImagesVisible()
    }

    @Test
    fun newsImagesFailedToLoadDueToNetworkConnection() {
        utils.disableWifiAndData()
        //newsScreen.repeatUntil { activityTestRule.activity.isFinishing }
        newsScreen.newsScreenIsDisplayed()
        newsScreen.failedToLoadNewsMessageIsSeen()
    }

    @Test
    fun newsImageIsClickedAndUserIsNavigatedToExternalBrowser() {
        //newsScreen.repeatUntil { activityTestRule.activity.isFinishing }
        newsScreen.newsScreenIsDisplayed()
        newsScreen.hasNewsImagesVisible()
        newsScreen.clickOnFirstNewsImageWhenVisible()
        //You could check the following with an INTENT, but I just decided to do it this way...
        utils.userIsNavigatedToChrome()
    }
}
