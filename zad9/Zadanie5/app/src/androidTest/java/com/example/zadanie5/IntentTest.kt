package com.example.zadanie5


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class IntentTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun intentTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.productsRecyclerView),
                childAtPosition(
                    withClassName(`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        val textView = onView(
            allOf(
                withId(R.id.textView), withText("micc"),
                withParent(withParent(withId(android.R.id.content))),
            )
        )
        textView.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.productsRecyclerView),
                isDisplayed()
            )
        )
        button.check(doesNotExist())

        val button2 = onView(
            allOf(
                withId(R.id.backBtn), withText("GO BACK"),
            )
        )
        button2.check(matches(isDisplayed()))

        val materialButton = onView(
            allOf(
                withId(R.id.backBtn), withText("Go back"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val recyclerView2 = onView(
            allOf(
                withId(R.id.productsRecyclerView),
                withParent(withParent(withId(R.id.flFragment))),
                isDisplayed()
            )
        )
        recyclerView2.check(matches(isDisplayed()))
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
