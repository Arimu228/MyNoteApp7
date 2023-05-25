package com.example.a7month_project.presentation.ui.fragment.EditListFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.usecase.CreateNoteUseCase
import com.example.a7month_project.domain.usecase.EditNoteUseCase
import com.example.a7month_project.domain.utils.Resource
import com.example.a7month_project.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class EditListViewModel @Inject constructor(
    private val editNotes: EditNoteUseCase,
    private val createNotes: CreateNoteUseCase
) : ViewModel() {
    private val _editNotesState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNotesState = _editNotesState.asStateFlow()
    private val _createNotesState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNotesState = _createNotesState.asStateFlow()


    fun editNotes(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            editNotes.editNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _editNotesState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _editNotesState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null)
                            _editNotesState.value = UIState.Success(res.data)
                    }
                }
            }
        }
    }

    fun createNotes(note: Note){
        viewModelScope.launch {
            createNotes.createNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _createNotesState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _createNotesState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null)
                            _createNotesState.value = UIState.Success(res.data)
                    }
                }
            }
        }
    }

}