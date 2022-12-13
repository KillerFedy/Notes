package com.example.notesproject.repository

import androidx.lifecycle.LiveData
import com.example.notesproject.data.NoteDao
import com.example.notesproject.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}