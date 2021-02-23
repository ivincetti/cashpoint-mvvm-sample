package ru.vincetti.test.cashpointssample.core.data

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner
import ru.vincetti.test.cashpointssample.core.network.models.points.DepositPoint

sealed class PointsResult {

    class SUCCESS(val list: List<CashPoint>) : PointsResult()

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

