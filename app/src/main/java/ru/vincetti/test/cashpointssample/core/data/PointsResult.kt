package ru.vincetti.test.cashpointssample.core.data

sealed class PointsResult {

    class SUCCESS(val list: List<CashPointDetails>) : PointsResult()

    object ERROR : PointsResult()
}

sealed class DepositPointsResult {

    class SUCCESS(val list: List<CashPointDetails>) : DepositPointsResult()

    object ERROR : DepositPointsResult()
}

