package com.app.vutestapplication.testing

import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class IntervalOverLapDetectorTest {

    @Before
    fun setUp() {
    }


    // Interval_1 starts before Interval_2
    @Test
    fun overlappingIntervals_I1_before_I2_returns_false() {
        val interval1 = Interval(1, 5)
        val interval2 = Interval(8, 12)
        assertThat(IntervalOverLapDetector.isOverlap(interval1, interval2), `is`(false))
    }

    // Interval_1 starts after Interval_2
    @Test
    fun overlappingIntervals_I1_after_I2_returns_false() {
        val interval1 = Interval(8, 12)
        val interval2 = Interval(1, 5)
        assertThat(IntervalOverLapDetector.isOverlap(interval2, interval1), `is`(false))
    }

    // Interval_1 starts end of Interval_2 (invalid test case)
    @Ignore("Not a valid test anymore")
    @Test
    fun overlappingIntervals_I1_starts_end_of_I2_returns_false() {
        val interval1 = Interval(1, 5)
        val interval2 = Interval(5, 10)
        assertThat(IntervalOverLapDetector.isOverlap(interval1, interval2), `is`(false))
    }

    // Interval_2 starts end of Interval_1
    @Test
    fun overlappingIntervals_I2_starts_end_of_I1_returns_false() {
        val interval1 = Interval(1, 5)
        val interval2 = Interval(6, 12)
        assertThat(IntervalOverLapDetector.isOverlap(interval1, interval2), `is`(false))
    }

    // Interval_1 is within Interval_2
    @Test
    fun overlappingIntervals_I1_includes_into_I2_returns_false() {
        val interval1 = Interval(9, 11)
        val interval2 = Interval(5, 15)
        assertThat(IntervalOverLapDetector.isOverlap(interval1, interval2), `is`(true))
    }

    // Interval_2 is within Interval_1
    @Test
    fun overlappingIntervals_I2_includes_into_I1_returns_false() {
        val interval1 = Interval(1, 5)
        val interval2 = Interval(2, 4)
        assertThat(IntervalOverLapDetector.isOverlap(interval1, interval2), `is`(true))
    }

}