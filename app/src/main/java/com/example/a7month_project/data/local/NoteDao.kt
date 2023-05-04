package com.example.a7month_project.data.local

import androidx.room.*
import com.example.a7month_project.data.model.NoteEntity

@Dao
interface NoteDao {
    // CRUD

    @Insert
    suspend fun createNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NoteEntity>

    @Update
    suspend fun editNote(noteEntity: NoteEntity)

    @Delete
    suspend fun  removeNote(noteEntity: NoteEntity)
}