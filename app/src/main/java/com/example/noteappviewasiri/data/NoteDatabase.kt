package com.example.noteappviewasiri.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// when we need to add new column or table in database must be update version = 1 and so on.
@Database(entities = [Note::class],version = 1,exportSchema = false)
/* note:
Database class must be abstract class
Data access object must be interface
 */

abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
    // How we want access to database
    companion object {
        @Volatile  // writes to this field are immediately visible to other threads
        private var instance: NoteDatabase? = null;

        // we use singleton instance to grantee no multiple connection with database
        fun getInstance(context: Context): NoteDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {  // protection from concurrent execution on multiple threads
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "myNoteDB"// we can see it on device
                ).fallbackToDestructiveMigration()  // Destroys old database on version change
                    .build()
                Companion.instance = instance
                return instance
            }

        }
    }
}
