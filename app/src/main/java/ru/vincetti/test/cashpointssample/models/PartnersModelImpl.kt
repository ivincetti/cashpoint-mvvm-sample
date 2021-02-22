package ru.vincetti.test.cashpointssample.models

import ru.vincetti.test.cashpointssample.core.network.models.partners.Partner
import javax.inject.Inject

class PartnersModelImpl @Inject constructor() : MutablePartnersModel {

    private var partnerList = listOf<Partner>()

    override fun setPartners(list: List<Partner>) {
        this.partnerList = list
    }

    override fun getPartnerById(id: String): Partner? {
        return partnerList.firstOrNull { it.id == id }
    }
}
