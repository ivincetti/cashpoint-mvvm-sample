package ru.vincetti.test.cashpointssample.models

sealed class PointsResult {

    class SUCCESS(val list: List<CashPoint>) : PointsResult()

    object ERROR : PointsResult()
}

