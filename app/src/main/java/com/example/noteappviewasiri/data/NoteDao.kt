package com.example.noteappviewasiri.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteappviewasiri.data.Note

@Dao
interface NoteDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insert (note : Note)

    @Delete
    suspend fun delete(note : Note)

    @Update
    suspend fun update (note : Note)

    @Query("Select * from Note ORDER BY id ASC ")
    fun load(): LiveData<List<Note>>

}