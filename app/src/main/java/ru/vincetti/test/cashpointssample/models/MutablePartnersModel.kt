package ru.vincetti.test.cashpointssample.models

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner

interface MutablePartnersModel : PartnersModel {

    fun setPartners(list: List<Partner>)
}