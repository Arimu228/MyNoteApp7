package com.example.a7month_project.domain.usecase

import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.repositories.NoteRepository

class CreateNoteUseCase (
    private val noteRepository: NoteRepository
) {
    fun createNote(note: Note) = noteRepository.createNote(note)
}