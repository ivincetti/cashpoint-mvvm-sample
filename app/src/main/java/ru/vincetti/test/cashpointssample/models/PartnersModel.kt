package ru.vincetti.test.cashpointssample.models

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner

interface PartnersModel {

    fun getPartnerById(id: String): Partner?
}
