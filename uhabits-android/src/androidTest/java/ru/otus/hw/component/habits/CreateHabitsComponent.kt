package ru.otus.hw.component.habits

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.isoron.uhabits.R
import ru.otus.hw.component.Component
import ru.otus.hw.helpers.*

class CreateHabitsComponent : Component() {
    override fun verify(): CreateHabitsComponent {
        checkThat(withText(CREATE_HABIT_TITLE), isDisplayed())
        return this
    }

    private fun enterName(): CreateHabitsComponent {
        sendTextWithReplace(withId(R.id.nameInput), HABIT_NAME)
        return this
    }

    private fun enterName2(): CreateHabitsComponent {
        sendTextWithReplace(withId(R.id.nameInput), (2..20).random().toString())
        return this
    }

    private fun enterQuestion(): CreateHabitsComponent {
        sendText(withId(R.id.questionInput), SOME_TEXT)
        return this
    }

    private fun selectFrequency(): CreateHabitsComponent {
        clickOn(withId(R.id.boolean_frequency_picker))
        clickOn(withId(R.id.everyXDaysRadioButton))
        clickOn(withText(SAVE))
        return this
    }

    private fun selectReminder(): CreateHabitsComponent {
        clickOn(withId(R.id.reminderTimePicker))
        clickOn(withId(R.id.done_button))
        return this
    }

    private fun enterNotes(): CreateHabitsComponent {
        sendText(withId(R.id.notesInput), SOME_TEXT)
        return this
    }

    private fun selectRandomColor(): CreateHabitsComponent {
        val randomColor = (0..19).random()

        clickOn(withId(R.id.colorButton))
        onView(withIndex(allOf(withId(R.id.color_picker_swatch)), randomColor) as Matcher<View>?)
            .perform(click())
        return this
    }

    private fun tapSaveButton(): CreateHabitsComponent {
        clickOn(withId(R.id.buttonSave))
        return this
    }

    internal fun create(): CreateHabitsComponent {
            enterName()
                .enterQuestion()
                .selectFrequency()
                .selectReminder()
                .enterNotes()
                .selectRandomColor()
                .tapSaveButton()
        return this
    }

    internal fun create2(): CreateHabitsComponent {
        enterName2()
            .enterQuestion()
            .selectFrequency()
            .selectReminder()
            .enterNotes()
            .selectRandomColor()
            .tapSaveButton()
        return this
    }

    internal fun checkHabitCreateMessage(): CreateHabitsComponent {
        checkThat(withText(HABIT_CREATED), isDisplayed())
        return this
    }
}