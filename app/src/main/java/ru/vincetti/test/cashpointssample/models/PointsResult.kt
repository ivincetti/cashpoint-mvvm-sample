package ru.vincetti.test.cashpointssample.models

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner
import ru.vincetti.test.cashpointssample.core.network.models.points.DepositPoint

sealed class PointsResult {

    class SUCCESS(val list: List<Point>) : PointsResult()

    object ERROR : PointsResult()
}

sealed class DepositPointsResult {

    class SUCCESS(val list: List<DepositPoint>) : DepositPointsResult()

    object ERROR : DepositPointsResult()
}

sealed class PartnersResult {

    class SUCCESS(val list: List<Partner>) : PartnersResult()

    object ERROR : PartnersResult()
}

