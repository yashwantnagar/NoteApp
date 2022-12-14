package com.org.note.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.org.note.R
import com.org.note.database.Note
import com.org.note.model.NoteViewModel

class MainActivity : AppCompatActivity(), NoteAdapter.NoteClickDeleteInterface,
    NoteAdapter.NoteClickInterface {

    lateinit var viewModel: NoteViewModel

    private val TAG : String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar : Toolbar = findViewById(R.id.toolbar)
        val recyclerView : RecyclerView = findViewById(R.id.noteRV)
        val addNote : FloatingActionButton = findViewById(R.id.addNote)


        toolBar.title = "Note"

        addNote.setOnClickListener( View.OnClickListener {

            val intent = Intent(this,WriteNoteActivity::class.java)
            startActivity(intent)
            finish()

        })


        recyclerView.layoutManager = LinearLayoutManager(this)

        val noteAdapter = NoteAdapter(this, this,this)

        recyclerView.adapter = noteAdapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                //on below line we are updating our list.
                noteAdapter.updateList(it)
            }
        })

        
    }

    override fun onDeleteIconClick(note: Note) {

        //in on note click method we are calling delete method from our viw modal to delete our not.
        viewModel.deleteNote(note)
        //displaying a toast message
        Toast.makeText(this, "${note.title} Deleted", Toast.LENGTH_LONG).show()

        Log.d(TAG, "onDeleteIconClick: ")

    }

    override fun onNoteClick(note: Note) {

        Log.d(TAG, "onNoteClick: ")
        
        //opening a new intent and passing a data to it.
        val intent = Intent(this, WriteNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDescription", note.note)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        finish()
        
        
    }


}