package com.example.liberatingdelta_kotlin1.db_files

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Inventory_Table")
class User_Inventory(//what is the name of this item
    @field:ColumnInfo(name = "LABEL") var name: String, //is it a weapon? item? etc
    @field:ColumnInfo(name = "TYPE") var type: String, //how many uses are left? if inf put -1
    @field:ColumnInfo(name = "AMT") var amount: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "inventory"
    }

}