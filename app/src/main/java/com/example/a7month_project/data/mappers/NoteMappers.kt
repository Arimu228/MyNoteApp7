package com.example.a7month_project.data.mappers

import com.example.a7month_project.data.model.NoteEntity
import com.example.a7month_project.domain.model.Note

fun Note.toEntity() = NoteEntity(id, title, desc)
fun NoteEntity.toNote() = Note(id, title, desc)