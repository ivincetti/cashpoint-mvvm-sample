package ru.vincetti.test.cashpointssample.models

interface PointsModel {

    fun getPoints(): List<CashPoint>

    fun findPointById(id: Int): CashPoint?
}
