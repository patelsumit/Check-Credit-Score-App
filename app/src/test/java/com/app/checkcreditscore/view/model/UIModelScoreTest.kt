package com.app.checkcreditscore.view.model

import org.junit.Assert
import org.junit.Test

class UIModelScoreTest {
    private val values = UIModelScore.values().map { it.value }

    @Test
    fun getNoneType() {
        assertEquals(
            expectedType = UIModelScore.NONE,
            actualType = UIModelScore.getInt(values[0])
        )
    }

    @Test
    fun getVeryPoor() {
        assertEquals(
            expectedType = UIModelScore.VERY_POOR,
            actualType = UIModelScore.getInt(values[1])
        )
    }

    @Test
    fun getPoor() {
        assertEquals(
            expectedType = UIModelScore.POOR,
            actualType = UIModelScore.getInt(values[2])
        )
    }

    @Test
    fun getFair() {
        assertEquals(
            expectedType = UIModelScore.FAIR,
            actualType = UIModelScore.getInt(values[3])
        )
    }

    @Test
    fun getGood() {
        assertEquals(
            expectedType = UIModelScore.GOOD,
            actualType = UIModelScore.getInt(values[4])
        )
    }

    @Test
    fun getVeryGood() {
        assertEquals(
            expectedType = UIModelScore.VERY_GOOD,
            actualType = UIModelScore.getInt(values[5])
        )
    }

    @Test
    fun getExcellent() {
        assertEquals(
            expectedType = UIModelScore.EXCELLENT,
            actualType = UIModelScore.getInt(values[6])
        )
    }

    @Test
    fun getAllType() {
        val count = values.size
        Assert.assertEquals(
            "all $count score type",
            7,
            count
        )
    }

    private fun assertEquals(expectedType: UIModelScore, actualType: UIModelScore) {
        Assert.assertEquals(expectedType, actualType)
    }
}