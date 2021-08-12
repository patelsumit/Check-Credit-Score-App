package com.app.checkcreditscore.domain

import org.junit.Assert
import org.junit.Test

class ScoreTypeTest {

    private val typeValues = ScoreStatus.values().map { it.values }

    @Test
    fun unknown() {
        assertEquals(
            expectedType = ScoreStatus.NONE,
            actualType = ScoreStatus.getScore(typeValues[0])
        )
    }

    @Test
    fun veryPoor() {
        assertEquals(
            expectedType = ScoreStatus.VPOOR,
            actualType = ScoreStatus.getScore(typeValues[1])
        )
    }

    @Test
    fun poor() {
        assertEquals(
            expectedType = ScoreStatus.POOR,
            actualType = ScoreStatus.getScore(typeValues[2])
        )
    }

    @Test
    fun fair() {
        assertEquals(
            expectedType = ScoreStatus.FAIR,
            actualType = ScoreStatus.getScore(typeValues[3])
        )
    }

    @Test
    fun good() {
        assertEquals(
            expectedType = ScoreStatus.GOOD,
            actualType = ScoreStatus.getScore(typeValues[4])
        )
    }

    @Test
    fun veryGood() {
        assertEquals(
            expectedType = ScoreStatus.VGOOD,
            actualType = ScoreStatus.getScore(typeValues[5])
        )
    }

    @Test
    fun excellent() {
        assertEquals(
            expectedType = ScoreStatus.EXCLT,
            actualType = ScoreStatus.getScore(typeValues[6])
        )
    }

    @Test
    fun fallback() {
        assertEquals(
            expectedType = ScoreStatus.NONE,
            actualType = ScoreStatus.getScore(Int.MIN_VALUE)
        )
    }

    @Test
    fun all() {
        Assert.assertEquals(
            "All Score Type...",
            7,
            typeValues.size
        )
    }

    private fun assertEquals(
        expectedType: ScoreStatus,
        actualType: ScoreStatus
    ) {
        Assert.assertEquals(expectedType, actualType)
    }
}