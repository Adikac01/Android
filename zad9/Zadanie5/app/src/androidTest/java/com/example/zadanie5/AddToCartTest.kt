package com.example.zadanie5


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
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
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.zadanie5.Functions.clickItemWithId

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddToCartTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun addToCartTest() {
        onView(
            withId(R.id.productsRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    0, clickItemWithId(R.id.addBtn)
                )
            )

        val editText = onView(
            allOf(
                withId(R.id.numberOfItems), withHint("Nummber of items"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        editText.check(matches(withHint("Nummber of items")))

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
        textInputEditText.perform(click())

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
        textInputEditText2.perform(replaceText("2"), closeSoftKeyboard())

        val editText2 = onView(
            allOf(
                withId(R.id.numberOfItems), withText("2"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("2")))

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

        val textView = onView(
            allOf(
                withId(R.id.price), withText("30.0"),
                withParent(withParent(withId(R.id.productCellContainer))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("30.0")))

        val textView2 = onView(
            allOf(
                withId(R.id.itemsInCart), withText("2"),
                withParent(withParent(withId(R.id.productCellContainer))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("2")))

        val textView3 = onView(
            allOf(
                withId(R.id.itemsInCart), withText("2"),
                withParent(withParent(withId(R.id.productCellContainer))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("2")))

        onView(withId(R.id.buyButton)).check(matches(withText("BUY (60.0 zł)")))
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
