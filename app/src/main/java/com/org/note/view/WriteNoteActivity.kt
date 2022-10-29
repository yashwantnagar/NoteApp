package com.org.note.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.org.note.R
import com.org.note.database.Note
import com.org.note.model.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class WriteNoteActivity : AppCompatActivity() {

    private lateinit var titleET : TextInputEditText
    lateinit var descET : TextInputEditText
    lateinit var dateTV : TextView
    private lateinit var toolbar : Toolbar
    private lateinit var btnSave : AppCompatButton

    private lateinit var dates : String


    //on below line we are creating variable for viewmodal and and integer for our note id.
    lateinit var viewModal: NoteViewModel
    var noteID = -1;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_note)

        toolbar  = findViewById(R.id.toolbar)
        titleET  = findViewById(R.id.titleET)
        btnSave  = findViewById(R.id.btnSave)

        descET  = findViewById(R.id.descET)
        dateTV  = findViewById(R.id.dateTV)

//        val simpleDateFormat = SimpleDateFormat("dd MM yyyy G 'at' HH:mm:ss z")
        val simpleDateFormat = SimpleDateFormat("dd MM yyyy")

        val currentDateAndTime : String = simpleDateFormat.format(Date())

        dates  = simpleDateFormat.format(Date())

        dateTV.text = currentDateAndTime
        dateTV.text = dates


        //on below line we are initlaiing our view modal.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)




        //on below line we are getting data passsed via an intent.
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            //on below line we are setting data to edit text.
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            btnSave.setText("Update Note")
            titleET.setText(noteTitle)
            descET.setText(noteDescription)
        } else {
            btnSave.setText("Save Note")
        }



//        string = titleET.text.toString()



//        toolbar.setOnClickListener(View.OnClickListener {
//
//            val intent = Intent()
//            intent.putExtra("text","I am back")
//            intent.putExtra("title",string)
//            setResult(MainActivity.resultCode1,intent)
//            finish()
//
//        })


        btnSave.setOnClickListener(View.OnClickListener {



            //on below line we are getting title and desc from edit text.
            val noteTitle = titleET.text.toString()
            val noteDescription = descET.text.toString()
            //on below line we are checking the type and then saving or updating the data.
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = noteID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    //if the string is not empty we are calling a add note method to add data to our room database.
                    viewModal.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            //opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()


//            string = titleET.text.toString()


//            val intent = Intent()
//            intent.putExtra("text","I am back")
//            intent.putExtra("title",titleET.text.toString())
//            setResult(MainActivity.resultCode1,intent)
//            finish()

        })

    }

}