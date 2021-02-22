package ru.vincetti.test.cashpointssample.core.storage.repo

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner

interface MutablePartnersRepo : PartnersRepo {

    fun setPartners(list: List<Partner>)
}
