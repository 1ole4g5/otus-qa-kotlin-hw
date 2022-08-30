package ru.otus.hw.component


import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.isoron.uhabits.R
import ru.otus.hw.helpers.INTRO_TITLE
import ru.otus.hw.helpers.SKIP

class IntroComponent : Component() {

    override fun verify(): IntroComponent {
        checkThat(allOf(withId(R.id.title), withText(INTRO_TITLE)), isDisplayed())
        return this
    }

    internal fun selectSkipButton() {
        clickOn(withContentDescription(SKIP))
    }
}