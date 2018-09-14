package com.diogo.weread.features.login

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
class LoginActivityTests {

    @get:Rule
    val activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun checkErrorMessage_bothFieldsBlank() {
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.loginEmailEditText))
                .check(matches(hasErrorText("Required field")))

        onView(withId(R.id.loginPasswordEditText))
                .check(matches(hasErrorText("Required field")))
    }

}