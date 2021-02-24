package ru.vincetti.test.cashpointssample.mvvm

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.core.network.models.partners.DailyLimit
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.ui.point.PointFragment
import ru.vincetti.test.cashpointssample.utils.HtmlText
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class PointViewModel(
    private val storage: Storage
) : ViewModel() {

    val needToNavigateToMap = SingleLiveEvent<Boolean>()

    val isLoading = SingleLiveEvent<Boolean>()
    val isError = SingleLiveEvent<Boolean>()

    val point = SingleLiveEvent<CashPointDetails>()
    val workHours: LiveData<String> = Transformations.map(point) {
        it.workHours ?: ""
    }
    val phones: LiveData<String> = Transformations.map(point) {
        it.phones ?: ""
    }
    val dailyLimits: LiveData<String> = Transformations.map(point) {
        getLimitsString(it.dailyLimits)
    }
    val description: LiveData<CharSequence> = Transformations.map(point) {
        it.description?.let { description ->
            HtmlText.fromHtml(description)
        } ?: ""
    }

    fun load(id: String) {
        isLoading.value = true
        if (id == PointFragment.EXTRA_POINT_ID_NO_DATA) {
            getError()
        } else {
            viewModelScope.launch {
                storage.getPointDetailById(id)?.let {
                    point.value = it
                } ?: kotlin.run {
                    getError()
                }
            }
        }
        isLoading.value = false
    }

    fun onBackClicked() {
        needToNavigateToMap.value = true
    }

    private fun getError() {
        isError.value = true
    }

    private fun getLimitsString(limits: List<DailyLimit>?): String {
        val stringBuilder = StringBuilder()
        limits?.let {
            if (it.isNotEmpty()) {
                it.forEach { limit ->
                    stringBuilder.appendLine("${limit.currency.name}: ${limit.amount}")
                }
                stringBuilder.setLength(stringBuilder.length - 1)
            }
        }
        return stringBuilder.toString()
    }
}

class PointViewModelFactory(
    private val storage: Storage
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PointViewModel(storage) as T
    }
}
