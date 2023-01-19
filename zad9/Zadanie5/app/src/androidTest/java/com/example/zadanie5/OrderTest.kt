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
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class OrderTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun orderTest() {

        onView(
            withId(R.id.productsRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ProductsViewHolder>(
                    2, Functions.clickItemWithId(R.id.addBtn)
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
        textInputEditText.perform(replaceText("3"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.numberOfItems), withText("3"),
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
                withId(R.id.price), withText("99.99"),
                withParent(withParent(withId(R.id.productCellContainer))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("99.99")))

        val textView2 = onView(
            allOf(
                withId(R.id.cartProductName), withText("Mikrofon"),
                withParent(withParent(withId(R.id.productCellContainer))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Mikrofon")))

        val button = onView(
            allOf(
                withId(R.id.buyButton)
            )
        )
        button.check(matches(withText("BUY (299.97 zł)")))

        val materialButton2 = onView(
            allOf(
                withId(R.id.buyButton),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.flFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val editText = onView(
            allOf(
                withId(R.id.email),
                isDisplayed()
            )
        )
        editText.check(matches(withHint("Email adress")))

        val editText2 = onView(
            allOf(
                withId(R.id.firstName),
                isDisplayed()
            )
        )
        editText2.check(matches(withHint("First Name")))

        val appCompatEditText = onView(
            allOf(
                withId(R.id.email),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.design_bottom_sheet),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(click())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.email),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.design_bottom_sheet),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("Adikac919@gmail.com"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.firstName),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.design_bottom_sheet),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("Adikac"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.lastName),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.design_bottom_sheet),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText(""), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.lastName),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.design_bottom_sheet),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(click())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.lastName),
                childAtPosition(
                    childAtPosition(
                        withId(com.google.android.material.R.id.design_bottom_sheet),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(replaceText("Kacperski"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.proceed), withText("Proceed"),
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
        materialButton3.perform(click())

        val button2 = onView(
            allOf(
                withId(R.id.buyButton),
                isDisplayed()
            )
        )
        button2.check(matches(withText("BUY (0.0 zł)")))

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

        val textView3 = onView(
            allOf(
                withId(R.id.person),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Adikac Kacperski")))

        val textView4 = onView(
            allOf(
                withId(R.id.combinedCost),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("299.97 zł")))
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
