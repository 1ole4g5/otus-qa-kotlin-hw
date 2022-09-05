package ru.otus.hw.tests

import androidx.compose.ui.test.*
import androidx.test.espresso.Espresso
import org.junit.Before
import org.junit.Test
import ru.otus.hw.helpers.HABIT_NAME
import ru.otus.hw.helpers.HABIT_TYPE
import ru.otus.hw.helpers.SKIP
import ru.otus.hw.helpers.testScreenshots


class MyHabitsInstrumentedTests : BaseTests() {

    @Before
    fun preCondition() {
        // select skip button
        composeActivity.onNodeWithText(SKIP).performClick()
    }

    @Test
    fun testMyHabit() {
        testScreenshots(name = "Main_page")
        // select add habit
        composeActivity.onNodeWithText("Add habit").performClick()
        // select habit type
        composeActivity.onNodeWithText(HABIT_TYPE).performClick()
        testScreenshots(name = "Create_habit_page")
        // create habit
        // enter name
        composeActivity.onNodeWithText("Name").performTextInput(HABIT_NAME)
        // click on save button
        composeActivity.onNodeWithText("Save").performClick()
        // scroll to created habit
        composeActivity.onNodeWithText(HABIT_NAME).performScrollTo()
        // select created habit
        composeActivity.onNodeWithText(HABIT_NAME).performClick()
        testScreenshots(name = "Check_created_habit")
        // check title of created habit
        composeActivity.onNodeWithText(HABIT_NAME).assertIsDisplayed()
        testScreenshots(name = "Check_created_habit_description")
        // press back button
        Espresso.pressBack()
        // select habit checkmark first button
        composeActivity.onAllNodesWithText("CheckmarkButtonView").onFirst().performClick()
        testScreenshots(name = "Check_mark_pop-up")
        // select Yes button
        composeActivity.onNodeWithText("yesBtn").performClick()
        testScreenshots(name = "Check_completed_habit")
        // select filter
        composeActivity.onNodeWithText("Filter").performClick()
        // toggle completed
        composeActivity.onNodeWithText("Hide completed").performClick()
        // check that habit is not displayed
        composeActivity.onAllNodesWithText(HABIT_NAME).assertCountEquals(0)
        testScreenshots(name = "Check_that_habit_is_not_in_not-completed_habit_filter")

        // переход на страницу профиля, проверка значения состояния здоровья;
        // composeActivity.onNodeWithText("Profile").performClick()
        // composeActivity.onNodeWithText("Health info").assertIsDisplayed()
        //
        // нажатие на кнопку "Поделиться" и проверка вызова Intent.ACTION_SEND;
        //
        // intending(not(isInternal())).respondWith(Instrumentation.ActivityResult(
        //                Activity.RESULT_OK,
        //                null
        //            )
        //        )
        //
        //      composeActivity.onNodeWithText("ShareTo").performClick()
        //
        //        intended(
        //            allOf(
        //                hasAction(Intent.ACTION_SEND),
        //                hasData(Uri.parse("shareTo:"))
        //            )
        //        )
    }
}