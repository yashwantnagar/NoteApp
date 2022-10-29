package com.org.note.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
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


    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    lateinit var viewModel: NoteViewModel

    private val TAG : String = "MainActivity"

    companion object {
        const val resultCode1 = 1200
        const val resultCode2 = 1300
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolBar : Toolbar = findViewById(R.id.toolbar)
        val recyclerView : RecyclerView = findViewById(R.id.noteRV)
        val addNote : FloatingActionButton = findViewById(R.id.addNote)


        toolBar.title = "Note"

        addNote.setOnClickListener( View.OnClickListener {

            Toast.makeText(this,"Yashwant",Toast.LENGTH_SHORT).show()

            val intent = Intent(this,WriteNoteActivity::class.java)
            startActivity(intent)
            finish()


//            activityResultLauncher.launch(intent)

        })


        recyclerView.layoutManager = LinearLayoutManager(this)

//        val data = ArrayList<NoteShowModel>()
//
//        for (i in 1..20){
//            data.add(NoteShowModel("Title $i", "Item $i",""))
//        }

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




        /*activityResultLauncher = registerForActivityResult(ActivityResultContracts
            .StartActivityForResult()) { result ->

            // There are no request codes
            if (result.resultCode == resultCode1 && result.data != null) {

                val t1 = result.data!!.getStringExtra("text")
                val title = result.data!!.getStringExtra("title")

                Log.d("TAG", "resultCode1: $t1 ")
                Log.d("TAG", "title: $title ")

            } else if (result.resultCode == resultCode2 && result.data != null){

                val t2 = result.data!!.getStringExtra("text")

                Log.d("TAG", "resultCode2: $t2 ")

            }
        }*/

        
    }

    override fun onDeleteIconClick(note: Note) {
//        TODO("Not yet implemented")

        //in on note click method we are calling delete method from our viw modal to delete our not.
        viewModel.deleteNote(note)
        //displaying a toast message
        Toast.makeText(this, "${note.title} Deleted", Toast.LENGTH_LONG).show()

        Log.d(TAG, "onDeleteIconClick: ")

    }

    override fun onNoteClick(note: Note) {
//        TODO("Not yet implemented")

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