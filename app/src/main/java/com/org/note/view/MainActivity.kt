package com.org.note.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.org.note.R
import com.org.note.model.NoteShowModel

class MainActivity : AppCompatActivity() {


    private lateinit var activityResultLauncher : ActivityResultLauncher<Intent>


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


//            activityResultLauncher.launch(intent)

        })


        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<NoteShowModel>()

        for (i in 1..20){
            data.add(NoteShowModel("Title $i", "Item $i",""))
        }

        val noteAdapter = NoteAdapter(this, data)

        recyclerView.adapter = noteAdapter





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
}