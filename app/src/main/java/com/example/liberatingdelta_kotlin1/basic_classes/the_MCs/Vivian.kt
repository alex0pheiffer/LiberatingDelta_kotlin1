package com.example.liberatingdelta_kotlin1.basic_classes.the_MCs

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Weapons.basicSword
import com.example.liberatingdelta_kotlin1.basic_classes.main_character
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine

class Vivian(exp: Int) : main_character(
    "Vivian",
    arrayOf("Main Character's Sister"),
    arrayOf(1),
    arrayOf("Hello."),
    "Female",
    17,
    170,
    true,
    65,
    7,
    characterTypes[17],
    magicTypes[2],
    R.drawable.character_vivian1,
    R.drawable.characterbattle_katie1,
    basicSword(),
    null,
    null,
    stats_object(3, 10, 0, 0, 130, 10, 10, 10, 10, 10),
    exp,
    PL_VendingMachine.getInitLevel(exp)
) {

    override fun toString(): String {
        return "Vivian"
    }
}