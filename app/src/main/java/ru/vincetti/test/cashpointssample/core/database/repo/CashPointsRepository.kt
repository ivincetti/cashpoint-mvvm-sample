package ru.vincetti.test.cashpointssample.core.database.repo

import ru.vincetti.test.cashpointssample.core.database.dao.CashPointsDao
import ru.vincetti.test.cashpointssample.core.database.models.DepositPointSQL

class CashPointsRepository(private val cashPointsDao: CashPointsDao) {

    suspend fun save(list: List<DepositPointSQL>) {
        cashPointsDao.insertPoints(list)
    }

    suspend fun deleteAll() {
        cashPointsDao.deleteAll()
    }
}