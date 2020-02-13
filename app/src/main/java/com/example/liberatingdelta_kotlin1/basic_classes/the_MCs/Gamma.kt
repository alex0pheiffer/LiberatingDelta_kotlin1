package com.example.liberatingdelta_kotlin1.basic_classes.the_MCs

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Weapons.basicSword
import com.example.liberatingdelta_kotlin1.basic_classes.main_character
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine

class Gamma(exp: Int) : main_character(
    "Gamma",
    arrayOf("Other Main Character!"),
    arrayOf(1),
    arrayOf("Hi there."),
    "Male",
    27,
    210,
    false,
    50,
    9,
    characterTypes[9],
    magicTypes[2],
    R.drawable.character_delta1,
    R.drawable.characterbattle_delta1,
    basicSword(),
    null,
    null,
    stats_object(5, 10, 200, 0, 0, 20, 50, 0, 0, 0),
    exp,
    PL_VendingMachine.getInitLevel(exp)
) {

    override fun toString(): String {
        return "Gamma"
    }
}