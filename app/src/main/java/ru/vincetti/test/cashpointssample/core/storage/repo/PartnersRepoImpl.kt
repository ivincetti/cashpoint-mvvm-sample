package ru.vincetti.test.cashpointssample.core.storage.repo

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner
import javax.inject.Inject

class PartnersRepoImpl @Inject constructor() : MutablePartnersRepo {

    private var partnerList = listOf<Partner>()

    override fun setPartners(list: List<Partner>) {
        this.partnerList = list
    }

    override fun getPartnerById(id: String): Partner? {
        return partnerList.firstOrNull { it.id == id }
    }
}
