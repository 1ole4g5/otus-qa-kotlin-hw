package ru.otus.hw.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.otus.hw.component.CheckmarkComponent
import ru.otus.hw.component.Component
import ru.otus.hw.component.Component.Companion.on
import ru.otus.hw.component.FilterComponent
import ru.otus.hw.component.IntroComponent
import ru.otus.hw.component.habits.CreateHabitsComponent
import ru.otus.hw.component.habits.HabitComponent
import ru.otus.hw.component.habits.HabitTypeComponent
import ru.otus.hw.component.habits.ListOfHabitsComponent
import ru.otus.hw.helpers.testScreenshots


@RunWith(AndroidJUnit4::class)
class MyHabitsInstrumentedTests : BaseTests() {

    @Before
    fun preCondition() {
        on<IntroComponent>().selectSkipButton()
    }

    @Test
    fun testMyHabit() {
        testScreenshots(name = "Main_page")
        on<ListOfHabitsComponent>().selectAddHabit()
        on<HabitTypeComponent>().selectHabitType()
        testScreenshots(name = "Create_habit_page")
        on<CreateHabitsComponent>().create()
            .checkHabitCreateMessage()
        on<ListOfHabitsComponent>().scrollToCreatedHabit()
            .selectCreatedHabit()
        testScreenshots(name = "Check_created_habit")
        on<HabitComponent>().checkHabitTitleName()
            .checkFrequencyLabel()
        testScreenshots(name = "Check_created_habit_description")
        on<Component>().pressBackButton()
        on<ListOfHabitsComponent>().selectHabitCheckmarkButton(1)
        testScreenshots(name = "Check_mark_pop-up")
        on<CheckmarkComponent>().selectYesButton()
        testScreenshots(name = "Check_completed_habit")
        on<FilterComponent>().selectFilter()
            .toggleCompleted()
        on<ListOfHabitsComponent>().checkThatHabitIsNotDisplayed()
        testScreenshots(name = "Check_that_habit_is_not_in_not-completed_habit_filter")

        // переход на страницу профиля, проверка значения состояния здоровья;
        // on<ProfileComponent>.selectProfile()
        //          .checkHealthState()
        // нажатие на кнопку "Поделиться" и проверка вызова Intent.ACTION_SEND;
        //
        // intending(not(isInternal())).respondWith(Instrumentation.ActivityResult(
        //                Activity.RESULT_OK,
        //                null
        //            )
        //        )
        //        on<ShareComponent>.selectShare() //onView(withId(R.id.share_id_button)).perform(click())
        //
        //        intended(
        //            allOf(
        //                hasAction(Intent.ACTION_SEND),
        //                hasData(Uri.parse("shareTo:"))
        //            )
        //        )
    }
}