package com.app.vutestapplication.testing

import android.widget.EditText
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import androidx.test.rule.ActivityTestRule
import com.app.vutestapplication.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestingActivityTest {

    private lateinit var activity: TestingActivity
    private lateinit var email: EditText
    private lateinit var text: TextView


    @get:Rule var activityTestRule = ActivityTestRule(TestingActivity::class.java)

    @Before
    fun setUp() {
        activity = activityTestRule.activity
        email = activity.findViewById(R.id.etEmail)
        text = activity.findViewById(R.id.tvName)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isTextValueIsHelloTest() {
        assertTrue(text.text.toString() == "Hello")
    }

    @Test
    fun isEmailValidTest() {
        //assertTrue(activity.isEmailValid())
        assertFalse(activity.isEmailValid(email = email.text.toString()))
    }
}