package com.example.zadanie5


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CategoryBasicTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun categoryBasicTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.miCategories), withContentDescription("Category"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNavigationView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.categoryName), withText("Mouse"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Mouse")))

        val imageView = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_icon_view),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_icon_container),
                        withParent(allOf(withId(R.id.miOrders), withContentDescription("Orders")))
                    )
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(com.google.android.material.R.id.navigation_bar_item_icon_view),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.navigation_bar_item_icon_container),
                        withParent(allOf(withId(R.id.miOrders), withContentDescription("Orders")))
                    )
                ),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.miOrders), withContentDescription("Orders"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNavigationView),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())
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
