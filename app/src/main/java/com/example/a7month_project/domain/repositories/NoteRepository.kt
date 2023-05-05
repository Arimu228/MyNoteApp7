package com.example.a7month_project.domain.repositories

import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun createNote(note: Note): Flow<Resource<Unit>>

    fun getAllNotes(): Flow<Resource<List<Note>>>

    fun editNote(note: Note): Flow<Resource<Unit>>

    fun removeNote(note: Note): Flow<Resource<Unit>>
}