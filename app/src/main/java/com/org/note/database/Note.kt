package com.org.note.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(

//    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val title : String,
    val note : String,
    val date : String
//    val time : String

//    var dates : Long =System.currentTimeMillis()

) {
    @PrimaryKey(autoGenerate = true) var id = 0

}
