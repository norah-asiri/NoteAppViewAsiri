package com.example.noteappviewasiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.noteappviewasiri.adapter.NoteAdapter
import com.example.noteappviewasiri.data.Note

class MainActivity : AppCompatActivity() {

    private lateinit var edNote: EditText
    private lateinit var btnSave: Button
    private lateinit var rvNotes: RecyclerView
    lateinit var notes: List<Note>
    lateinit var mainViewModel: NoteViewModel
    private lateinit var rvAdapter: NoteAdapter

   // ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        mainViewModel.getNotes().observe(this, {
                notes -> rvAdapter.update(notes)
        })
        rvNotes = findViewById(R.id.recyclerView)
        edNote = findViewById(R.id.etNote)
        btnSave = findViewById(R.id.Save)
        notes = listOf()

        btnSave.setOnClickListener {
            mainViewModel.addNote(edNote.text.toString())
            edNote.text.clear()
            edNote.clearFocus()
        }
        rvAdapter = NoteAdapter(this)
        rvNotes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = rvAdapter
    }

    fun raiseDialog(id: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ ->mainViewModel.editNote(id, updatedNote.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }

}

/*
        edNote = findViewById(R.id.etNote)
        rvNotes = findViewById(R.id.recyclerView)
        btnSave = findViewById(R.id.Save)



        btnSave.setOnClickListener {
            postNote()

        }
        updateRV()
    }

    // update RV to show new notes
    private fun updateRV(){

        CoroutineScope(Dispatchers.IO).launch {
            var f = NoteDatabase.getInstance(applicationContext).NoteDao().load()
            // tv.text = f.get(0).location
            rvNotes.adapter = NoteAdapter(applicationContext as MainActivity,f)
            rvNotes.layoutManager = LinearLayoutManager(applicationContext)
        }
    }

    // get list of notes
    private fun load(): List<Note>{
        return NoteDatabase.getInstance(applicationContext).NoteDao().load() }

    // add note
    private fun postNote(){
        val note= edNote.text.toString()
        val s = Note(0,note)
        CoroutineScope(Dispatchers.IO).launch {
            NoteDatabase.getInstance(applicationContext).NoteDao().insert(s)
        }
        Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT)
            .show();

        updateRV()
    }


    // edit Note
    private fun editNote(noteID: Int, noteText: String){
        NoteDatabase.getInstance(applicationContext).NoteDao().update(Note(noteID, noteText))
        updateRV()
    }

    // delete Note
    private fun del(noteID: Int){
        NoteDatabase.getInstance(applicationContext).NoteDao().update(Note(noteID, ""))
        updateRV()
    }

    //confirmation alert
    fun deleteNote(noteID: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    _, _ -> del(noteID)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Are you sure?")
        alert.show()
    }

    fun raiseDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ -> editNote(id, updatedNote.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }
}

 */
