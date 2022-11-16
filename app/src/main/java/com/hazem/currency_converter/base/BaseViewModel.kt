package com.hazem.currency_converter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.currency_converter.utils.network.ApplicationException
import com.hazem.currency_converter.utils.network.ErrorType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {
    inline fun wrapBlockingOperation(
        crossinline function: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch {
            try {
                function()
            } catch (throwable: Throwable) {
                handelError(throwable)
                Timber.e(throwable)
            }
        }
    }

    open fun handelError(throwable: Throwable) {
        if (throwable is ApplicationException) {
            // todo handle different types of errors according to requirements
            when (throwable.type) {
                ErrorType.Network.Unauthorized -> {}
                ErrorType.Network.ResourceNotFound -> {}
                ErrorType.Network.Unexpected -> {}
                ErrorType.Network.NoInternetConnection -> {}
                else -> {}
            }
        }
    }
}