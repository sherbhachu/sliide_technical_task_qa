package com.test.news.features.login.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import junit.framework.AssertionFailedError
import org.hamcrest.core.AllOf.allOf
import java.lang.Exception

open class BaseScreen {

    fun waitForIdWithTextToBeCompletelyVisible(viewId: Int, text: String) {
        repeatUntil {  onView(allOf(withId(viewId), withText(text))).check(matches(isCompletelyDisplayed())) }
    }

    fun repeatUntil(codeblock: () -> Unit) {
        repeat(100) {
            try {
                codeblock()
                return
            } catch (e: Exception) {
                Thread.sleep(250)
            } catch (e: AssertionError) {
                Thread.sleep(250)
            } catch (e: AssertionFailedError) {
                Thread.sleep(250)
            }
        }
        throw NoSuchElementException("Unable to find requested element")
    }

}