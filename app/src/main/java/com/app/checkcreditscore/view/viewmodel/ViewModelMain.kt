package com.app.checkcreditscore.view.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.checkcreditscore.domain.model.response.EntityMainResponse
import com.app.checkcreditscore.domain.usecase.UseCase
import com.app.checkcreditscore.domain.usecase.UseCaseFactory
import com.app.checkcreditscore.util.DataState
import com.app.checkcreditscore.util.exhaustive
import com.app.checkcreditscore.view.model.UIModel
import com.app.checkcreditscore.view.model.transform.transform
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// view mode injection to load data and set state
class ViewModelMain @ViewModelInject constructor(
    useCaseOverviewGetFactory: UseCaseFactory<EntityMainResponse>
) : ViewModel() {
    private val useCaseOverviewGet: UseCase<EntityMainResponse> =
        useCaseOverviewGetFactory.create()

    private val _dataState: MutableLiveData<DataState<UIModel>> = MutableLiveData()
    val dataState: LiveData<DataState<UIModel>>
        get() = _dataState

    fun setStateEvent(event: StateEvent) {
        when (event) {
            is StateEvent.None -> {
            }

            is StateEvent.GetOverview -> getOverview()
        }.exhaustive
    }

    private fun getOverview() {
        launchDataLoad {
            val response = useCaseOverviewGet.execute()
            if (response.isOkay) {
                _dataState.value = DataState.Success(response.entityMain.transform())
                Log.e("Response : ", _dataState.value.toString())
            } else {
                _dataState.value =
                    DataState.Error(IllegalStateException(response.message))
            }
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            _dataState.value = DataState.Loading
            try {
                block()
                Log.e("Main : ", _dataState.value.toString())
            } catch (ex: Exception) {
                _dataState.value = DataState.Error(ex)
            }
        }
    }

    sealed class StateEvent {
        object None : StateEvent()
        object GetOverview : StateEvent()
    }
}