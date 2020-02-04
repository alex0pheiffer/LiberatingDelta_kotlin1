package com.example.liberatingdelta_kotlin1.basic_classes

abstract class Mweapon(
    val name: String,
    val buff: stats_object,
    val Mtype: Int,
    val mPerc: Int
) : Weapon(name, buff, 0) {
    val type: String
    //"Fire","Water","Land","Air","Match"
    private val mTypes = arrayOf(
        Characters.magicTypes[1],
        Characters.magicTypes[2],
        Characters.magicTypes[3],
        Characters.magicTypes[4],
        "Match"
    )

    override fun toString(): String {
        return "Mweapon"
    }

    init {
        //if 4 ("match") the magic type is to match that of the char-user
        type = mTypes[Mtype]
    }
}