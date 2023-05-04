package com.example.a7month_project.domain.usecase

import com.example.a7month_project.domain.repositories.NoteRepository

class GetAllNotesUseCase (
    private val noteRepository: NoteRepository
){
    fun getAllNotes() = noteRepository.getAllNotes()
}