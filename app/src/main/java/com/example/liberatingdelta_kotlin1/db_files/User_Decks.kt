package com.example.liberatingdelta_kotlin1.db_files

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Decks_Table")
class User_Decks(//the name of the deck
    @field:ColumnInfo(name = "LABEL") var name: String,
    @field:ColumnInfo(name = "CHAR") var char_equip: String, //number of cards in the deck
    @field:ColumnInfo(name = "LEN") var length: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "decks"
    }

}