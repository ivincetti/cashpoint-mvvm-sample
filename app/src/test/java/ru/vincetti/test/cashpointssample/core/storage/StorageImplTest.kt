package ru.vincetti.test.cashpointssample.core.storage

import org.junit.Assert
import org.junit.Test
import ru.vincetti.test.cashpointssample.core.storage.StorageImpl.Companion.TIME_CACHE_VALIDITY_MILLIS

class StorageImplTest {

    @Test
    fun `checkDateValid not valid time return false`() {
        Assert.assertFalse(
            StorageImpl.checkDateValid(NOW, NOT_VALID_TIME)
        )
    }

    @Test
    fun `checkDateValid valid time return true`() {
        Assert.assertTrue(
            StorageImpl.checkDateValid(NOW, VALID_TIME)
        )
    }

    companion object {
        private const val NOW = 50_000_000L
        private const val VALID_TIME = NOW - TIME_CACHE_VALIDITY_MILLIS + 1_000L
        private const val NOT_VALID_TIME = NOW - TIME_CACHE_VALIDITY_MILLIS - 1000L
    }
}
