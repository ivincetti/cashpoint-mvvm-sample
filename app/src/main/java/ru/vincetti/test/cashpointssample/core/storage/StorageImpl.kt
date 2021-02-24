package ru.vincetti.test.cashpointssample.core.storage

import androidx.annotation.VisibleForTesting
import com.google.android.gms.maps.model.LatLng
import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.core.data.DepositPointsResult
import ru.vincetti.test.cashpointssample.core.data.PointsResult
import ru.vincetti.test.cashpointssample.core.database.models.DepositPointSQL
import ru.vincetti.test.cashpointssample.core.database.models.PartnerSQL
import ru.vincetti.test.cashpointssample.core.database.models.PointsRequest
import ru.vincetti.test.cashpointssample.core.database.repo.CashPointsRepository
import ru.vincetti.test.cashpointssample.core.database.repo.MainPointsRepository
import ru.vincetti.test.cashpointssample.core.database.repo.PartnersRepository
import ru.vincetti.test.cashpointssample.core.database.repo.PointsRequestRepository
import ru.vincetti.test.cashpointssample.core.network.DOWNLOAD_OK
import ru.vincetti.test.cashpointssample.core.network.TinkoffService
import java.util.*
import javax.inject.Inject

class StorageImpl @Inject constructor(
    private val mainPointsRepository: MainPointsRepository,
    private val pointsRequestRepository: PointsRequestRepository,
    private val pointsRepository: CashPointsRepository,
    private val partnersRepository: PartnersRepository,
    private val networkService: TinkoffService
) : Storage {

    override suspend fun getPointsForMap(
        latLng: LatLng,
        radius: Double
    ): PointsResult {
        return when (val pointsData = getDepositPoints(latLng.latitude, latLng.longitude, radius)) {
            is DepositPointsResult.SUCCESS -> pointsData.list.let {
                PointsResult.SUCCESS(it)
            }
            else -> PointsResult.ERROR
        }
    }

    override suspend fun getPointById(id: String): CashPointDetails? {
        return mainPointsRepository.getPointById(id)
    }

    private suspend fun getDepositPoints(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): DepositPointsResult {
        val lastRequest =
            pointsRequestRepository.getLastUpdateDate(latitude, longitude, radius.toLong())
        val now = Date().time
        val isCacheValid = checkPartnerValid(now, lastRequest)
        return if (isCacheValid) {
            getPointsFromCache(lastRequest!!.id)?.let {
                DepositPointsResult.SUCCESS(it)
            } ?: DepositPointsResult.ERROR
        } else {
            pointsRequestRepository.deleteAll()
            partnersRepository.deleteAll()
            pointsRepository.deleteAll()
            val requestID = pointsRequestRepository.create(
                PointsRequest(
                    date = Date().time,
                    radius = radius.toLong(),
                    latitude = latitude,
                    longitude = longitude
                )
            )
            if (
                getPointsFromOriginalSuccess(latitude, longitude, radius, requestID)
                && getPartnersFromOriginalSuccess(requestID)
            ) {
                getPointsFromCache(requestID)?.let {
                    DepositPointsResult.SUCCESS(it)
                } ?: DepositPointsResult.ERROR
            } else {
                pointsRequestRepository.delete(requestID)
                DepositPointsResult.ERROR
            }
        }
    }

    private suspend fun getPointsFromCache(requestID: Long): List<CashPointDetails>? {
        return mainPointsRepository.getAll(requestID)
    }

    private suspend fun getPointsFromOriginalSuccess(
        latitude: Double,
        longitude: Double,
        radius: Double,
        requestID: Long
    ): Boolean {
        return try {
            val data = networkService.getDepositPoints(latitude, longitude, radius.toLong())
            if (data.resultCode == DOWNLOAD_OK) {
                val listForSave = data.depositPointList.map {
                    DepositPointSQL(
                        externalId = it.externalId,
                        pointsRequestId = requestID,
                        fullAddress = it.fullAddress,
                        latitude = it.location.latitude,
                        longitude = it.location.longitude,
                        partnerName = it.partnerName,
                        phones = it.phones ?: "",
                        workHours = it.workHours ?: ""
                    )
                }
                pointsRepository.save(listForSave)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun getPartnersFromOriginalSuccess(requestID: Long): Boolean {
        return try {
            val data = networkService.getPartners()
            if (data.resultCode == DOWNLOAD_OK) {
                val listForSave = data.partnersList.map {
                    PartnerSQL(
                        partnerName = it.id,
                        partnerRequestId = requestID,
                        description = it.description,
                        name = it.name,
                        picture = it.picture
                    )
                }
                partnersRepository.save(listForSave)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    companion object {

        const val TIME_CACHE_VALIDITY_MILLIS = 10 * 60 * 1000

        private fun checkPartnerValid(now: Long, request: PointsRequest?): Boolean {
            return request?.let {
                it.date + TIME_CACHE_VALIDITY_MILLIS > now
            } ?: false
        }

        @VisibleForTesting
        fun checkDateValid(now: Long, cache: Long): Boolean {
            return cache + TIME_CACHE_VALIDITY_MILLIS > now
        }

    }
}
