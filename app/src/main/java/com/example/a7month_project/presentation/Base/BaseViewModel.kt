package com.example.a7month_project.presentation.Base

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.utils.Resource
import com.example.a7month_project.presentation.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected fun <T> Flow<Resource<T>>.collectData(_state: MutableStateFlow<UIState<List<Note>>>) {
        viewModelScope.launch {
            this@collectData.collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _state.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null)
                            _state.value = UIState.Succes(res.data)
                    }
                }
            }
        }
    }

}