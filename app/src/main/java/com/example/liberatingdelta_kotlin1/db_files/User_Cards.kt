package com.example.liberatingdelta_kotlin1.db_files

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Cards_Table")
class User_Cards(
    @field:ColumnInfo(name = "LABEL") var name: String,
    @field:ColumnInfo(name = "AMT") var amount: Int,
    @field:ColumnInfo(name = "DECK") var deck: String,
    @field:ColumnInfo(name = "POS") var position: Int
    )
{
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "cards"
    }

}