package com.org.note.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.org.note.R
import com.org.note.database.Note

class NoteAdapter(

    private val context : Context,
    private val noteClickDeleteInterface : NoteClickDeleteInterface,
    private val noteClickInterface : NoteClickInterface

    ) :

    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.show_notes, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        //on below line we are setting data to item of recycler view.
        holder.noteTitle.setText(allNotes.get(position).title)
        holder.noteDesc.setText(allNotes.get(position).note)
        holder.noteDate.setText("Last Updated : "+allNotes.get(position).date)
        //on below line we are adding click listner to our delete image view icon.

       /* holder.deleteIV.setOnClickListener {
            //on below line we are calling a note click interface and we are passing a position to it.
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }*/

        //on below line we are adding click listner to our recycler view item.
        holder.itemView.setOnClickListener {
            //on below line we are calling a note click interface and we are passing a position to it.
            noteClickInterface.onNoteClick(allNotes.get(position))

            Log.d("TAG", "onBindViewHolder: ")

        }



        /*holder.cardView.setOnClickListener {

            Toast.makeText(context, "noteShowModel.title",
                Toast.LENGTH_SHORT).show()


            noteClickInterface.onNoteClick(allNotes.get(position))

        }*/


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return allNotes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val cardView : CardView = itemView.findViewById(R.id.cardView)
        val noteTitle : TextView = itemView.findViewById(R.id.noteTitle)
        val noteDesc : TextView = itemView.findViewById(R.id.noteDesc)
        val noteDate : TextView = itemView.findViewById(R.id.noteDate)

    }


    fun updateList(newList : List<Note>){

        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()

    }

    interface NoteClickDeleteInterface {
        fun onDeleteIconClick(note: Note)
    }

    interface NoteClickInterface {
        fun onNoteClick(note: Note)
    }

}