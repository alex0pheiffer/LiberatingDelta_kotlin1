package com.example.liberatingdelta_kotlin1.basic_classes.the_MCs

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.main_character
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine

class Katherine(exp: Int) : main_character(
    "Katherine",
    arrayOf("The main Character!"),
    arrayOf(Integer.valueOf(1)),
    arrayOf("Yippee!"),
    "Female",
    14,
    163,
    true,
    80,
    5,
    characterTypes[17],
    magicTypes[0],
    R.drawable.character_katie1,
    R.drawable.characterbattle_katie1,
    null,
    null,
    null,
    stats_object(3, 10, 0, 100, 0, 10, 0, 0, 0, 0),
    exp,
    PL_VendingMachine.getInitLevel(exp)
) {

    override fun toString(): String {
        return "Katherine"
    }
}