package ru.vincetti.test.cashpointssample.core.database.repo

import ru.vincetti.test.cashpointssample.core.database.dao.PartnersDao
import ru.vincetti.test.cashpointssample.core.database.models.PartnerSQL

class PartnersRepository(private val partnersDao: PartnersDao) {

    suspend fun save(list: List<PartnerSQL>) {
        partnersDao.insertPoints(list)
    }

    suspend fun deleteAll() {
        partnersDao.deleteAll()
    }
}