package ru.vincetti.test.cashpointssample.core.storage

import com.google.android.gms.maps.model.LatLng
import ru.vincetti.test.cashpointssample.core.network.DOWNLOAD_OK
import ru.vincetti.test.cashpointssample.core.network.TinkoffService
import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner
import ru.vincetti.test.cashpointssample.models.*
import javax.inject.Inject

class StorageImpl @Inject constructor(
    private val pointsModel: MutablePointsModel,
    private val partnerModel: MutablePartnersModel,
    private val networkService: TinkoffService
) : Storage {

    override suspend fun getPoints(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): PointsResult {
        val pointsData = getDepositPoints(latitude, longitude, radius)
        val partnerData = getPartnersList()
        return if (pointsData is PointsResult.ERROR || partnerData is PartnersResult.ERROR) {
            PointsResult.ERROR
        } else {
            (pointsData as PointsResult.SUCCESS).also {
                pointsModel.setPoints(it.list)
                partnerModel.setPartners(
                    (partnerData as PartnersResult.SUCCESS).list
                )
            }

        }
    }

    override fun getPointById(id: String): CashPoint? {
        return pointsModel.getPointById(id)
    }

    override fun getPartnerById(id: String): Partner? {
        return partnerModel.getPartnerById(id)
    }

    private suspend fun getDepositPoints(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): PointsResult {
        val data = networkService.getDepositPoints(latitude, longitude, radius.toLong())
        return if (data.resultCode != DOWNLOAD_OK) {
            PointsResult.ERROR
        } else {
            val list = data.depositPointList.map { point ->
                CashPoint(
                    point.externalId,
                    LatLng(point.location.latitude, point.location.longitude),
                    point.partnerName,
                    point.fullAddress
                )
            }
            PointsResult.SUCCESS(list)
        }
    }

    private suspend fun getPartnersList(): PartnersResult {
        val data = networkService.getPartners()
        return if (data.resultCode != DOWNLOAD_OK) {
            PartnersResult.ERROR
        } else {
            PartnersResult.SUCCESS(data.partnersList)
        }
    }
}
