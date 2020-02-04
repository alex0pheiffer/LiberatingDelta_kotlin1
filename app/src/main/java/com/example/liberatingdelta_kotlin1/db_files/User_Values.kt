package com.example.liberatingdelta_kotlin1.db_files

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Values_Table")
class User_Values(
    @field:ColumnInfo(name = "PL") var cur_PL: Int,
    @field:ColumnInfo(name = "PHASE") var cur_phase: Int,
    @field:ColumnInfo(name = "OKANE") var cur_okane: Int,
    @field:ColumnInfo(name = "FRONTCHAR") var front_char: String,
    @field:ColumnInfo(name = "USERNAME") var username: String,
    @field:ColumnInfo(name = "PASSWORD") var password: String,
    @field:ColumnInfo(name = "REGION") var cur_region: String
)
{
    @PrimaryKey(autoGenerate = true)
     var id = 0

    override fun toString(): String {
        return "values"
    }

}