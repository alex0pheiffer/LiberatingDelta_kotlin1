package com.example.liberatingdelta_kotlin1.db_files

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_EQPlayed_Table")
class User_EQPlayed(
    @field:ColumnInfo(name = "LABEL") var name: String, //this number ranges from 0 to inf
    @field:ColumnInfo(name = "COMP") var completed: Int, //this number ranges from 0 to inf
    @field:ColumnInfo(name = "SIGITEM") var sig_item_collected: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "eqplayed"
    }

}