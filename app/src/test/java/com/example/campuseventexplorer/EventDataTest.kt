package com.example.campuseventexplorer

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EventDataTest {

    @Test
    fun hasAtLeastEightEvents() {
        assertTrue(sampleEvents.size >= 8)
    }

    @Test
    fun idsAreUnique() {
        assertEquals(sampleEvents.size, sampleEvents.map { it.id }.toSet().size)
    }

    @Test
    fun blankQueryMatchesEverything() {
        assertEquals(sampleEvents.size, sampleEvents.count { it.matches("") })
        assertEquals(sampleEvents.size, sampleEvents.count { it.matches("   ") })
    }

    @Test
    fun matchesTitleIgnoringCase() {
        val result = sampleEvents.filter { it.matches("mobile") }
        assertEquals(listOf("Mobile Development Meetup"), result.map { it.title })
    }

    @Test
    fun matchesCategory() {
        val result = sampleEvents.filter { it.matches("career") }
        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.category == "Career" })
    }

    @Test
    fun doesNotMatchLocationOrDescriptionOnly() {
        // "CAS 214" appears only in a location, which the search must not use.
        assertTrue(sampleEvents.none { it.matches("CAS 214") })
    }

    @Test
    fun unknownIdFindsNothing() {
        assertEquals(null, sampleEvents.firstOrNull { it.id == 999L })
    }
}
