package com.daniel.pruebadeingreso.ui.users

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.daniel.pruebadeingreso.R
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(MainActivity::class.java)

    @Test
    fun testCaseVisibleForRecyclerView(){
        onView(withId(R.id.rc_users))
            .check(matches(isDisplayed()))
        onView(withId(R.id.txt_empty_user)).check(matches(not(isDisplayed())))
    }

    @Test
    fun testCaseSearchNoExits(){
        onView(withId(R.id.et_search_user))
            .check(matches(isDisplayed()))
        onView(withId(R.id.et_search_user))
            .perform(replaceText("Daniel"))
        onView(withId(R.id.txt_empty_user)).check(matches(isDisplayed()))
    }

    @Test
    fun testCaseSearchExitsData(){
        onView(withId(R.id.et_search_user))
            .check(matches(isDisplayed()))
        onView(withId(R.id.et_search_user))
            .perform(replaceText("G"))
        onView(withId(R.id.rc_users))
            .check(matches(isDisplayed()))
    }

}