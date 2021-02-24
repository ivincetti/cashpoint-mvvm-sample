package ru.vincetti.test.cashpointssample.core.database.repo

import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.core.database.dao.PointsDao

class MainPointsRepository(private val pointsDao: PointsDao) {

    suspend fun getAll(requestID: Long): List<CashPointDetails>? {
        return pointsDao.getAll(requestID)
    }

    suspend fun getPointById(id: String): CashPointDetails? {
        return pointsDao.getPointById(id)
    }
}