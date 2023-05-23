package com.example.a7month_project.data.repositories

import com.example.a7month_project.data.base.BaseRepo
import com.example.a7month_project.data.local.NoteDao
import com.example.a7month_project.data.mappers.toEntity
import com.example.a7month_project.data.mappers.toNote
import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.repositories.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao

) : BaseRepo(), NoteRepository {
    override fun createNote(note: Note) = doRequest {
        noteDao.createNote(note.toEntity())
    }

    override fun getAllNotes() = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: Note)= doRequest {
        noteDao.editNote(note.toEntity())
    }

    override fun removeNote(note: Note)= doRequest {
        noteDao.removeNote(note.toEntity())
    }


}
