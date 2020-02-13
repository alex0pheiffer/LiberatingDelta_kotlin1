package com.example.liberatingdelta_kotlin1.basic_classes.Weapons

import com.example.liberatingdelta_kotlin1.basic_classes.Mweapon
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object

class SimpleStaff : Mweapon(
    "Simple Staff",
    stats_object(2, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    4,
    100
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