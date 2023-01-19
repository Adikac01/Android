package com.example.myapplication


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class BasicTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun basicTest() {
        val textView = onView(
            allOf(
                withId(R.id.res),
                withParent(withParent(withId(R.id.linearLayout))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("")))

        val materialButton = onView(
            allOf(
                withId(R.id.three), withText("3"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        2
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.three), withText("3"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        2
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.eq), withText("="),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        1
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        onView(withId(R.id.res)).check(matches(withText("33")))

        val materialButton4 = onView(
            allOf(
                withId(R.id.plus), withText("+"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        3
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(R.id.six), withText("6"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        3
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val textView3 = onView(
            allOf(
                withId(R.id.res),
            )
        )
        textView3.check(matches(withText("33+6")))

        val materialButton6 = onView(
            allOf(
                withId(R.id.eq), withText("="),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        1
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val materialButton7 = onView(
            allOf(
                withId(R.id.eq), withText("="),
            )
        )
        materialButton7.perform(click())

        val textView4 = onView(
            allOf(
                withId(R.id.res)
            )
        )
        textView4.check(matches(withText("39")))

        val textView5 = onView(
            allOf(
                withId(R.id.res), withText("33+6"),

            )
        )
        textView5.check(doesNotExist())

        val materialButton8 = onView(
            allOf(
                withId(R.id.eight), withText("8"),
            )
        )
        materialButton8.perform(click())

        val textView6 = onView(
            allOf(
                withId(R.id.res)
            )
        )
        textView6.check(matches(withText("39")))

        val materialButton9 = onView(
            allOf(
                withId(R.id.clear), withText("c"),
                isDisplayed()
            )
        )
        materialButton9.perform(click())

        val textView7 = onView(
            allOf(
                withId(R.id.res)
            )
        )
        textView7.check(matches(withText("")))
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
