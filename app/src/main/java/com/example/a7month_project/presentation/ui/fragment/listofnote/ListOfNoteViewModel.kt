package com.example.noteapp.presentation.ui.fragment.listofnote


import androidx.lifecycle.viewModelScope
import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.usecase.GetAllNotesUseCase
import com.example.a7month_project.domain.usecase.RemoveNoteUseCase
import com.example.a7month_project.domain.utils.Resource
import com.example.noteapp.presentation.base.BaseViewModel
import com.example.a7month_project.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfNoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val removeNotesUseCase: RemoveNoteUseCase
) : BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUseCase.getAllNotes().collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _getAllNotesState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _getAllNotesState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null)
                            _getAllNotesState.value = UIState.Succes(res.data)
                    }
                }
            }
        }
    }

    fun removeNotes(){
        viewModelScope.launch {
            removeNotesUseCase.getAllNotes().collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _getAllNotesState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _getAllNotesState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null)
                            _getAllNotesState.value = UIState.Succes(res.data)
                    }
                }
            }
        }

    }

}

