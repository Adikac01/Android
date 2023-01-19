package com.example.zadanie5


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
class RemoveFromCartTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun removeFromCartTest() {
        onView(
            withId(R.id.productsRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    3, Functions.clickItemWithId(R.id.addBtn)
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
        textInputEditText.perform(replaceText("1"), closeSoftKeyboard())

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

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.miProducts), withContentDescription("Products"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNavigationView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        onView(
            withId(R.id.productsRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    0, Functions.clickItemWithId(R.id.addBtn)
                )
            )

        val textInputEditText2 = onView(
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
        textInputEditText2.perform(replaceText("1"), closeSoftKeyboard())

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

        val bottomNavigationItemView3 = onView(
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
        bottomNavigationItemView3.perform(click())

        val button = onView(
            allOf(
                withId(R.id.buyButton),
                isDisplayed()
            )
        )
        button.check(matches(withText("BUY (182.99 zł)")))

        onView(
            withId(R.id.cartRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    0, Functions.clickItemWithId(R.id.removeBtn)
                )
            )

        val button2 = onView(
            allOf(
                withId(R.id.buyButton),
                isDisplayed()
            )
        )
        button2.check(matches(withText("BUY (30.0 zł)")))

        val textView = onView(
            allOf(
                withId(R.id.cartProductName), withText("Mysz"),
                withParent(withParent(withId(R.id.productCellContainer))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Mysz")))

        onView(
            withId(R.id.cartRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    0, Functions.clickItemWithId(R.id.removeBtn)
                )
            )

        val button3 = onView(
            allOf(
                withId(R.id.buyButton), withText("BUY (0.0 zł)"),
                isDisplayed()
            )
        )
        button3.check(matches(isDisplayed()))
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
