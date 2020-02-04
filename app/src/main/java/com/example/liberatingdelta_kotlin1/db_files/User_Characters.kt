package com.example.liberatingdelta_kotlin1.db_files

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Characters_Table")
class User_Characters(
    @field:ColumnInfo(name = "LABEL") var name: String,
    @field:ColumnInfo(name = "LVL") var level: Int,
    @field:ColumnInfo(name = "RANK") var rank: Int,
    @field:ColumnInfo(name = "WEAPON") var weapon_equip: String,
    @field:ColumnInfo(name = "DECK") var deck_equip: String,
    @field:ColumnInfo(name = "ITEM") var item_equip: String,
    //set regionEXP
//
    //get regionEXP
//
    @field:ColumnInfo(name = "REGION1EXP") var region1exp: Int,
    @field:ColumnInfo(name = "REGION23EXP") var region23exp: Int,
    @field:ColumnInfo(name = "REGION4EXP") var region4exp: Int,
    @field:ColumnInfo(name = "REGION5EXP") var region5exp: Int,
    @field:ColumnInfo(name = "REGION6EXP") var region6exp: Int,
    @field:ColumnInfo(name = "REGION7EXP") var region7exp: Int,
    @field:ColumnInfo(name = "REGION89EXP") var region89exp: Int,
    @field:ColumnInfo(name = "REGION10EXP") var region10exp: Int,
    @field:ColumnInfo(name = "REGION11EXP") var region11exp: Int,
    @field:ColumnInfo(name = "REGION12EXP") var region12exp: Int,
    @field:ColumnInfo(name = "REGION13EXP") var region13exp: Int,
    @field:ColumnInfo(name = "REGION14EXP") var region14exp: Int,
    @field:ColumnInfo(name = "REGION16EXP") var region16exp: Int,
    @field:ColumnInfo(name = "REGION17EXP") var region17exp: Int,
    @field:ColumnInfo(name = "REGION18EXP") var region18exp: Int,
    @field:ColumnInfo(name = "REGION19EXP") var region19exp: Int,
    @field:ColumnInfo(name = "REGION20EXP") var region20exp: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "characters"
    }

}