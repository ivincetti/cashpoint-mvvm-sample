package ru.vincetti.test.cashpointssample.core.database.repo

import ru.vincetti.test.cashpointssample.core.database.dao.PointsRequestDao
import ru.vincetti.test.cashpointssample.core.database.models.PointsRequest

class PointsRequestRepository(private val pointsRequestDao: PointsRequestDao) {

    suspend fun getLastUpdateDate(
        latitude: Double,
        longitude: Double,
        radius: Long
    ): PointsRequest? {
        return pointsRequestDao.getDate(latitude, longitude, radius)
    }

    suspend fun create(request: PointsRequest): Long {
        return pointsRequestDao.insertRequest(request)
    }

    suspend fun delete(requestID: Long) {
        pointsRequestDao.deleteById(requestID)
    }

    suspend fun deleteAll() {
        pointsRequestDao.deleteAll()
    }
}
