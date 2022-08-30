package ru.otus.hw.component

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matcher
import ru.otus.hw.helpers.waitUntilViewIsDisplayed

open class Component {

    inline fun <reified T : Component> on(): T {
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    companion object {
        inline fun <reified T : Component> on(): T {
            return Component().on()
        }
    }

    open fun verify(): Component {
        return this
    }

    fun checkThat(matcher: Matcher<View>, assertionMatcher: Matcher<View>) {
        waitUntilViewIsDisplayed(matcher)
        onView(matcher).check(ViewAssertions.matches(assertionMatcher))
    }

    fun sendText(matcher: Matcher<View>, string: String) {
        waitUntilViewIsDisplayed(matcher)
        onView(matcher).perform(typeText(string), click())
    }

    fun sendTextWithReplace(matcher: Matcher<View>, string: String) {
        waitUntilViewIsDisplayed(matcher)
        onView(matcher).perform(replaceText(string), click())
    }

    fun clickOn(matcher: Matcher<View>) {
        waitUntilViewIsDisplayed(matcher)
        onView(matcher).perform(click())
    }

    fun scrollToByRecycler(text: String) {
        onView(instanceOf(RecyclerView::class.java)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText(text))
            )
        )
    }

    fun pressBackButton() {
        Espresso.pressBack()
    }
}