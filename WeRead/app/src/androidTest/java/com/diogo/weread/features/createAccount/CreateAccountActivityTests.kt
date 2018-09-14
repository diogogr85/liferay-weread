package com.diogo.weread.features.createAccount

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.diogo.weread.R

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CreateAccountActivityTests {

    @get:Rule
    val activityRule: ActivityTestRule<CreateAccountActivity> =
            ActivityTestRule(CreateAccountActivity::class.java)

    @Test
    fun checkErrorMessage_withFieldsBlank() {
        onView(withId(R.id.createAccountButton)).perform(click())

        onView(withId(R.id.createAccountNameEditText))
                .check(matches(hasErrorText("Required field")))

        onView(withId(R.id.createAccountEmailEditText))
                .check(matches(hasErrorText("Required field")))

        onView(withId(R.id.createAccountPasswordEditText))
                .check(matches(hasErrorText("Required field")))
    }

}