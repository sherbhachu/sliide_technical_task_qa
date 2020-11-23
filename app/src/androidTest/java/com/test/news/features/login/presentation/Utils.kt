package com.test.news.features.login.presentation

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector

class Utils : BaseScreen() {

    fun sendAppToBackgroundThenResume() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        device.pressRecentApps()
        val selector = UiSelector()
        val recentApp = device.findObject(selector.descriptionContains("NEWS"))
        Thread.sleep(1000)
        recentApp.click()
    }

    fun getCurrentPackage() : String {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        return device.currentPackageName
    }

    fun enableWifiAndData() {
        sendShellCommand("svc wifi enable", "svc data enable")
        Thread.sleep(1000)
    }

    fun disableWifiAndData() {
        sendShellCommand("svc wifi disable", "svc data disable")
    }

    private fun sendShellCommand(wifiOption: String, dataOption: String)  {
        val options = arrayOf(wifiOption, dataOption)
        for (option in options) {
            InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(option)
        }
    }

    fun userIsNavigatedToChrome() {
        repeatUntil { (getCurrentPackage() !== "com.test.news") }
        repeatUntil { (getCurrentPackage() == "com.android.chrome") }
    }
}