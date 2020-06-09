package com.app.vutestapplication.testing

import android.util.Log

object IntervalOverLapDetector {
    fun isOverlap(interval1: Interval, interval2: Interval): Boolean {
        val isOverlapped: Boolean = interval1.end >= interval2.start && interval1.start <= interval2.end
        Log.d("isOverlapped","value: $isOverlapped")
        return isOverlapped
    }
}

data class Interval(
    val start: Int,
    val end: Int
) {
    init {
        if (start >= end) throw IllegalArgumentException("START time can not be larger than END time!")
    }
}