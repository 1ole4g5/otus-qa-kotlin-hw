package ru.otus.hw.component

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.isoron.uhabits.R

class FilterComponent : Component() {

    internal fun selectFilter(): FilterComponent {
        clickOn(withId(R.id.action_filter))
        return this
    }

    internal fun toggleCompleted(): FilterComponent {
        clickOn(withText(R.string.hide_completed))
        return this
    }
}