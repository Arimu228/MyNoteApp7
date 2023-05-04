package com.example.a7month_project.domain.repositories

import com.example.a7month_project.domain.model.Note

interface NoteRepository {
    fun createNote(note: Note)

    fun getAllNotes(): List<Note>

    fun editNote(note: Note)

    fun removeNote(note: Note)
}