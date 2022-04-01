package uk.co.bubblebearapps.pantry.ext

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IntExtKtTest {

    @Test
    fun `0 is NOT in the range 1-2`() {
        assertFalse(0.isIn(1..2))
        assertTrue(0.isNotIn(1..2))
    }

    @Test
    fun `1 is in in the range 1-2`() {
        assertTrue(1.isIn(1..2))
        assertFalse(1.isNotIn(1..2))
    }

    @Test
    fun `2 is in the range 1-2`() {
        assertTrue(2.isIn(1..2))
        assertFalse(2.isNotIn(1..2))
    }

    @Test
    fun `3 is NOT in the range 1-3`() {
        assertFalse(3.isIn(1..2))
        assertTrue(3.isNotIn(1..2))
    }
}
