package ru.otus.hw.component.habits

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.Matcher
import org.isoron.uhabits.R
import ru.otus.hw.component.Component
import ru.otus.hw.helpers.HABIT_NAME
import ru.otus.hw.helpers.HABIT_TITLE
import ru.otus.hw.helpers.withIndex

class ListOfHabitsComponent : Component() {
    override fun verify(): ListOfHabitsComponent {
        checkThat(withText(HABIT_TITLE), isDisplayed())
        return this
    }

    internal fun selectAddHabit() {
        onView(withId(R.id.actionCreateHabit)).perform(click())
    }

    internal fun scrollToCreatedHabit(): ListOfHabitsComponent {
        scrollToByRecycler(HABIT_NAME)
        return this
    }

    internal fun selectCreatedHabit(): ListOfHabitsComponent {
        clickOn(withText(HABIT_NAME))
        return this
    }

    internal fun selectHabitCheckmarkButton(count: Int) {
        onView(
            withIndex(
                allOf(withClassName(endsWith("CheckmarkButtonView"))),
                count
            ) as Matcher<View>?
        ).perform(click())
    }

    internal fun checkThatHabitIsNotDisplayed() {
        onView(withText(HABIT_NAME))
            .check(doesNotExist())
    }
}