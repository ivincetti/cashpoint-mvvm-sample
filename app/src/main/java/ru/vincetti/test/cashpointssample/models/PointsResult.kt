package ru.vincetti.test.cashpointssample.models

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner

sealed class PointsResult {

    class SUCCESS(val list: List<CashPoint>) : PointsResult()

    object ERROR : PointsResult()
}

sealed class PartnersResult {

    class SUCCESS(val list: List<Partner>) : PartnersResult()

    object ERROR : PartnersResult()
}

