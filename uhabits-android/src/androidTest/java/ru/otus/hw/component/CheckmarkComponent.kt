package ru.otus.hw.component

import androidx.test.espresso.matcher.ViewMatchers.*
import org.isoron.uhabits.R

class CheckmarkComponent : Component() {

    override fun verify(): CheckmarkComponent {
        checkThat(withHint(R.string.notes), isDisplayed())
        return this
    }

    internal fun selectYesButton(): CheckmarkComponent {
        clickOn(withId(R.id.yesBtn))
        return this
    }
}