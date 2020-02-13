package com.example.liberatingdelta_kotlin1.basic_classes.Weapons

import com.example.liberatingdelta_kotlin1.basic_classes.Weapon
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object

class SimpleSword : Weapon(
    "Simple Sword",
    stats_object(2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    0
) {
    val instanceName: String

    override fun toString(): String {
        return javaClass.getSimpleName()
    }

    companion object {
        private var numInstance = 0
    }

    init {
        instanceName =
            javaClass.getSimpleName()+ " " + numInstance
        numInstance++
    }
}