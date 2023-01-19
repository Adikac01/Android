package com.example.zadanie4


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.newTaskBtn)
            )
        )
        recyclerView.check(doesNotExist())

        val extendedFloatingActionButton = onView(
            allOf(
                withId(R.id.newTaskBtn),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainer),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        extendedFloatingActionButton.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.name),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.textfield.TextInputLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("Do something"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.desc),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.textfield.TextInputLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("Fast"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.saveButton), withText("Save"),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.design_bottom_sheet),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val frameLayout = onView(
            allOf(
                withId(R.id.taskCellContainer),
                withParent(
                    allOf(
                        withId(R.id.todoListRecyclerView),
                        withParent(IsInstanceOf.instanceOf(android.widget.RelativeLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.name), withText("Do something"),
                withParent(withParent(withId(R.id.taskCellContainer))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Do something")))

        val imageButton = onView(
            allOf(
                withId(R.id.doneButton), withContentDescription("CheckBox"),
                withParent(withParent(withId(R.id.taskCellContainer))),
                withContentDescription(R.drawable.checked_24),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val appCompatImageButton = onView(
            allOf(
                withId(R.id.doneButton), withContentDescription("CheckBox"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.taskCellContainer),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val imageButton2 = onView(
            allOf(
                withId(R.id.doneButton), withContentDescription("CheckBox"),
                withParent(withParent(withId(R.id.taskCellContainer))),
                isDisplayed()
            )
        )
        imageButton2.check(matches(isDisplayed()))

        val imageButton3 = onView(
            allOf(
                withId(R.id.doneButton), withContentDescription("CheckBox"),
                withParent(withParent(withId(R.id.taskCellContainer))),
                isDisplayed()
            )
        )
        imageButton3.check(doesNotExist())

        val extendedFloatingActionButton2 = onView(
            allOf(
                withId(R.id.newTaskBtn),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainer),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        extendedFloatingActionButton2.perform(click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.name),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.textfield.TextInputLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("Do something else"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.saveButton), withText("Save"),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.design_bottom_sheet),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.name), withText("Do something else"),
                withParent(withParent(withId(R.id.taskCellContainer))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Do something else")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
