package com.daniel.pruebadeingreso.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class ScopedViewModel : ViewModel() , Scope by Scope.Impl() {

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }

}