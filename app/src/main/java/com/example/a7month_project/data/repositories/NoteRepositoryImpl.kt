package com.example.a7month_project.data.repositories

import com.example.a7month_project.data.local.NoteDao
import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.repositories.NoteRepository

class NoteRepositoryImpl (
    private val noteDao : NoteDao

): NoteRepository {
    override fun createNote(note: Note) {
        noteDao.createNote(note)
    }

    override fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    override fun editNote(note: Note) {
        return noteDao.editNote(note)
    }

    override fun removeNote(note: Note) {
        noteDao.removeNote(note)
    }

}