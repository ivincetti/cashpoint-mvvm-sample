package ru.vincetti.test.cashpointssample.core.storage

import com.google.android.gms.maps.model.LatLng
import ru.vincetti.test.cashpointssample.core.network.DOWNLOAD_OK
import ru.vincetti.test.cashpointssample.core.network.TinkoffService
import ru.vincetti.test.cashpointssample.models.CashPoint
import ru.vincetti.test.cashpointssample.models.MutablePointsModel
import ru.vincetti.test.cashpointssample.models.PointsResult
import javax.inject.Inject

class StorageImpl @Inject constructor(
    private val model: MutablePointsModel,
    private val networkService: TinkoffService
) : Storage {

    override suspend fun getDepositPoints(
        latitude: Double,
        longitude: Double,
        radius: Double
    ): PointsResult {
        val info = networkService.getDepositPoints(latitude, longitude, radius.toLong())
        return if (info.resultCode != DOWNLOAD_OK) {
            return PointsResult.ERROR
        } else {
            val list = info.payload.map { point ->
                CashPoint(
                    point.externalId,
                    LatLng(point.location.latitude, point.location.longitude),
                    point.partnerName,
                    point.fullAddress
                )
            }
            model.setPoint(list)
            PointsResult.SUCCESS(list)
        }
    }

    override fun findPointById(id: String): CashPoint? {
        return model.getPointById(id)
    }
}
