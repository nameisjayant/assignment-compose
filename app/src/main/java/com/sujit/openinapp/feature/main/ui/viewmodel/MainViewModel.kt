package com.sujit.openinapp.feature.main.ui.viewmodel

import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujit.openinapp.data.model.ApiModelResponse
import com.sujit.openinapp.data.repository.MainRepository
import com.sujit.openinapp.data.utils.doOnFailure
import com.sujit.openinapp.data.utils.doOnLoading
import com.sujit.openinapp.data.utils.doOnSuccess
import com.sujit.openinapp.preference.PreferenceStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceStore: PreferenceStore,
    private val repository: MainRepository
) : ViewModel() {

    private val _apiResponseEventFlow: MutableStateFlow<ComposeUi<ApiModelResponse>> =
        MutableStateFlow(
            ComposeUi()
        )
    var apiResponseEventFloe = _apiResponseEventFlow.asStateFlow()
        private set

    fun setPref(key: Preferences.Key<String>, value: String) =
        viewModelScope.launch { preferenceStore.setPref(key, value) }

    fun getPref(key: Preferences.Key<String>) = preferenceStore.getPref(key)

    fun onEvent(event: MainEvent) = viewModelScope.launch {
        when (event) {
            MainEvent.GetApiResponseEvent -> {
                repository.getApiResponse()
                    .doOnLoading {
                        _apiResponseEventFlow.value = ComposeUi(
                            isLoading = true
                        )
                    }
                    .doOnFailure {
                        _apiResponseEventFlow.value = ComposeUi(
                            error = it ?: "SOMETHING WENT WRONG"
                        )
                    }
                    .doOnSuccess {
                        _apiResponseEventFlow.value = ComposeUi(
                            data = it
                        )
                    }
                    .collect()
            }
        }
    }
}

data class ComposeUi<T>(
    val data: T? = null,
    val error: String = "",
    val isLoading: Boolean = false
)

sealed class MainEvent {

    data object GetApiResponseEvent : MainEvent()

}