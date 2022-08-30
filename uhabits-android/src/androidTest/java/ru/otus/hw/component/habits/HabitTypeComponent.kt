package ru.otus.hw.component.habits

import androidx.test.espresso.matcher.ViewMatchers.withText
import ru.otus.hw.component.Component
import ru.otus.hw.helpers.HABIT_TYPE

class HabitTypeComponent : Component() {
    override fun verify(): HabitTypeComponent {
        return this
    }

    internal fun selectHabitType(): HabitTypeComponent {
        clickOn(withText(HABIT_TYPE))
        return this
    }
}