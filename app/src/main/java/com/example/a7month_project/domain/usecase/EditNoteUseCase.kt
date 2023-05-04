package com.example.a7month_project.domain.usecase

import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.repositories.NoteRepository

class EditNoteUseCase (
    private val noteRepository: NoteRepository
) {
    fun editNote(note: Note) = noteRepository.editNote(note)
}