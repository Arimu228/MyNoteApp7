package com.example.a7month_project.domain.usecase

import com.example.a7month_project.domain.repositories.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){
    fun getAllNotes() = noteRepository.getAllNotes()
}