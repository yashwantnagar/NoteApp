package com.org.note.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.org.note.R
import java.text.SimpleDateFormat
import java.util.*

class WriteNoteActivity : AppCompatActivity() {

    private lateinit var titleET : TextInputEditText
    private lateinit var toolbar : Toolbar
    private lateinit var btnSave : AppCompatButton

    private lateinit var string: String

    private lateinit var dates : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_note)

        toolbar  = findViewById(R.id.toolbar)
        titleET  = findViewById(R.id.titleET)
        btnSave  = findViewById(R.id.btnSave)

        val descET : TextInputEditText = findViewById(R.id.descET)
        val dateTV : TextView = findViewById(R.id.dateTV)

//        val simpleDateFormat = SimpleDateFormat("dd MM yyyy G 'at' HH:mm:ss z")
        val simpleDateFormat = SimpleDateFormat("dd MM yyyy")
        val currentDateAndTime : String = simpleDateFormat.format(Date())

        dates  = simpleDateFormat.format(Date())

        dateTV.text = currentDateAndTime
        dateTV.text = dates


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

//            string = titleET.text.toString()


            val intent = Intent()
            intent.putExtra("text","I am back")
//            intent.putExtra("title",dates)
            intent.putExtra("title",titleET.text.toString())
            setResult(MainActivity.resultCode1,intent)
            finish()

        })

    }

}