package com.example.a7month_project.data.repositories

import com.example.a7month_project.data.local.NoteDao
import com.example.a7month_project.data.mappers.toEntity
import com.example.a7month_project.data.mappers.toNote
import com.example.a7month_project.domain.model.Note
import com.example.a7month_project.domain.repositories.NoteRepository
import com.example.a7month_project.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao : NoteDao

): NoteRepository {
    override fun createNote(note: Note): Flow<Resource<Unit>> {
        return flow{
            emit(Resource.Loading())
            try {
                val data = noteDao.createNote(note.toEntity())
                emit(Resource.Success(data))
            } catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getAllNotes(): Flow<Resource<List<Note>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = noteDao.getAllNotes()
                emit(Resource.Success(data.map { it.toNote() }))
            } catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun editNote(note: Note): Flow<Resource<Unit>> {
        return flow{
            emit(Resource.Loading())
            try {
                val data = noteDao.editNote(note.toEntity())
                emit(Resource.Success(data))
            } catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun removeNote(note: Note): Flow<Resource<Unit>> {
        return flow{
            emit(Resource.Loading())
            try {
                val data = noteDao.removeNote(note.toEntity())
                emit(Resource.Success(data))
            } catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: "unknown error"))
            }
        }.flowOn(Dispatchers.IO)
    }


}
