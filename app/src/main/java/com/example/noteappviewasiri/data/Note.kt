package com.example.noteappviewasiri.data

import android.content.Context
import androidx.room.*

/*
1- create data class for entity-> table
2- Define Doa = data accss object
3- create database
4- create data base instance
 */

@Entity //Next read as table
data class Note(
    @PrimaryKey (autoGenerate = true)@ColumnInfo(name="id") val id: Int=0,
    @ColumnInfo (name = "NoteText")val noteText: String
    )

/*
@Entity(tableName = "NotesTable")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteText: String)
 */







