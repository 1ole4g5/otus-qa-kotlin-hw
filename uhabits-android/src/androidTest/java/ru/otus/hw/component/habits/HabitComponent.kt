package ru.otus.hw.component.habits

import androidx.test.espresso.matcher.ViewMatchers.*
import org.isoron.uhabits.R
import ru.otus.hw.component.Component
import ru.otus.hw.helpers.FREQUENCY
import ru.otus.hw.helpers.HABIT_NAME

class HabitComponent : Component() {
    override fun verify(): HabitComponent {
        checkThat(withId(R.id.action_edit_habit), isDisplayed())
        return this
    }

    internal fun checkHabitTitleName(): HabitComponent {
        checkThat(withText(HABIT_NAME), isDisplayed())
        return this
    }

    internal fun checkFrequencyLabel(): HabitComponent {
        checkThat(withText(FREQUENCY), isDisplayed())
        return this
    }
}