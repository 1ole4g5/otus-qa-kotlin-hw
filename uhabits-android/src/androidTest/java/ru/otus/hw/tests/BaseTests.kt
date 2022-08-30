package ru.otus.hw.tests

import androidx.test.rule.ActivityTestRule
import org.isoron.uhabits.activities.habits.list.ListHabitsActivity
import org.junit.Rule


open class BaseTests {
    @get:Rule
    val activity = ActivityTestRule(ListHabitsActivity::class.java)
}