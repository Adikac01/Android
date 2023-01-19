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
class OtherTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun otherTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.dot), withText("."),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        5
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.dot), withText("."),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        5
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.res), withText("."),
                withParent(withParent(withId(R.id.linearLayout))),
                isDisplayed()
            )
        )
        textView.check(matches(withText(".")))

        val textView2 = onView(
            allOf(
                withId(R.id.res), withText(".."),
                withParent(withParent(withId(R.id.linearLayout))),
                isDisplayed()
            )
        )
        textView2.check(doesNotExist())

        val materialButton3 = onView(
            allOf(
                withId(R.id.div), withText("/"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        5
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(R.id.minus), withText("-"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        4
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(R.id.mult), withText("*"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        5
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val textView3 = onView(
            allOf(
                withId(R.id.res), withText("."),
                withParent(withParent(withId(R.id.linearLayout))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText(".")))

        val materialButton6 = onView(
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
        materialButton6.perform(click())

        val materialButton7 = onView(
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
        materialButton7.perform(click())

        val textView4 = onView(
            allOf(
                withId(R.id.res), withText("0.6"),
                withParent(withParent(withId(R.id.linearLayout))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("0.6")))

        val materialButton8 = onView(
            allOf(
                withId(R.id.pow), withText("^"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton8.perform(click())

        val materialButton9 = onView(
            allOf(
                withId(R.id.mult), withText("*"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        5
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton9.perform(click())

        val materialButton10 = onView(
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
        materialButton10.perform(click())

        val textView5 = onView(
            allOf(
                withId(R.id.res), withText("0.6*"),
                withParent(withParent(withId(R.id.linearLayout))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("0.6*")))
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
