package com.example.a7month_project.presentation.ui.fragment.listofnote

import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.usecase.GetAllNotesUseCase
import com.example.a7month_project.domain.usecase.RemoveNoteUseCase
import com.example.a7month_project.presentation.Base.BaseViewModel
import com.example.a7month_project.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject

@HiltViewModel
class ListOfNoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val removeNotesUseCase: RemoveNoteUseCase
) : BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _removeNotesState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val removeAllNotesState = _removeNotesState.asStateFlow()

    fun getAllNotes() {
        getAllNotesUseCase.getAllNotes().collectData(_getAllNotesState)
    }

    fun removeNotes(note:Note) {
        removeNotesUseCase.removeNote(note).collectData(_removeNotesState)

    }

}

