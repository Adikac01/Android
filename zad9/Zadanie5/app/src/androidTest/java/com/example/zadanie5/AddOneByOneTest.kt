package com.example.zadanie5


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.zadanie5.Adapters.ProductsViewHolder
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
class AddOneByOneTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addOneByOneTest() {
        onView(
            withId(R.id.productsRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    0, Functions.clickItemWithId(R.id.addBtn)
                )
            )

        val textInputEditText = onView(
            allOf(
                withId(R.id.numberOfItems),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("2"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.numberOfItems), withText("2"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(click())

        val materialButton = onView(
            allOf(
                withId(R.id.saveBtn), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        onView(
            withId(R.id.productsRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    0, Functions.clickItemWithId(R.id.addBtn)
                )
            )

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.numberOfItems),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("1"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.saveBtn), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.miCart), withContentDescription("Cart"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNavigationView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.itemsInCart),
                withParent(withParent(withId(R.id.productCellContainer))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("3")))

        onView(
            withId(R.id.cartRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    0, Functions.clickItemWithId(R.id.removeBtn)
                )
            )

        val recyclerView = onView(
            allOf(
                withId(R.id.productCellContainer),
                isDisplayed()
            )
        )
        recyclerView.check(doesNotExist())
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
