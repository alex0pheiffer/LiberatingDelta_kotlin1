package com.example.liberatingdelta_kotlin1.basic_classes

abstract class Weapon(name: String, buff: stats_object, charequip: Int = 0) :
    inventI(name, false, buff) {
    var isCharSpec = false
    val charEquip: String?
    private val charequipers =
        arrayOf("All", "Vivian", "Katherine", "Delta")

    override fun toString(): String {
        return javaClass.simpleName
    }

    init {
        charEquip = charequipers[charequip]
        if (charequip == 0) {
            isCharSpec = false
        } else {
            isCharSpec = true
        }
    }
}