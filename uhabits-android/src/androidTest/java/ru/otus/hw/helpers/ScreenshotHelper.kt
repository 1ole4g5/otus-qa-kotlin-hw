package ru.otus.hw.helpers

import androidx.test.runner.screenshot.Screenshot

fun testScreenshots(name: String) {
    val capture = Screenshot.capture()
    capture.name = "screen_$name.png"
    capture.process()
}