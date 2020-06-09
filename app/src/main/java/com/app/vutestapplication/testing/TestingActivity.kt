package com.app.vutestapplication.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.app.vutestapplication.R
import com.app.vutestapplication.utils.pop
import kotlinx.android.synthetic.main.activity_testing.*
import java.util.regex.Pattern

class TestingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)
        onClicks()

        // Checking
        val interval1 = Interval(1, 5)
        val interval2 = Interval(2, 4)
        IntervalOverLapDetector.isOverlap(interval1, interval2)
    }

    private fun onClicks() {
        btn.setOnClickListener {
            etEmail.setText("a@g.com")
            if (isEmailValid(etEmail.text.toString())) pop("Email is valid!") else pop("Email is not valid!")
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun overlappingIntervalsDetector() {
    }

}
