package com.diogo.weread.features.feeds

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FeedsActivityTest {

    @get:Rule
    val activityRule: ActivityTestRule<FeedsActivity> = ActivityTestRule(FeedsActivity::class.java)


}