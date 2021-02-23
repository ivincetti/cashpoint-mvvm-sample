package ru.vincetti.test.cashpointssample.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vincetti.test.cashpointssample.core.data.CashPointDetails
import ru.vincetti.test.cashpointssample.core.storage.Storage
import ru.vincetti.test.cashpointssample.ui.point.PointFragment
import ru.vincetti.test.cashpointssample.utils.SingleLiveEvent

class PointViewModel(
    private val storage: Storage
) : ViewModel() {

    val needToNavigateToMap = SingleLiveEvent<Boolean>()

    val isLoading = SingleLiveEvent<Boolean>()
    val isError = SingleLiveEvent<Boolean>()

    val point = SingleLiveEvent<CashPointDetails>()

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
}

class PointViewModelFactory(
    private val storage: Storage
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PointViewModel(storage) as T
    }
}
