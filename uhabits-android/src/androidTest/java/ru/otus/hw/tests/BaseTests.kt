package ru.otus.hw.tests

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.isoron.uhabits.activities.habits.list.ListHabitsActivity
import org.junit.Rule


open class BaseTests {
    @get:Rule
    val composeActivity = createAndroidComposeRule<ListHabitsActivity>()
}