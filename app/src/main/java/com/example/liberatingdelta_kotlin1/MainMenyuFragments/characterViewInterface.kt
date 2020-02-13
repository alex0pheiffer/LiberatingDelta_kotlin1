package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.basic_classes.main_character


interface characterViewInterfaceOut {
    fun grabMMC(): String
    fun grabCUR_PL(): PL
}

interface characterViewInterfaceIn {
    fun updateMMC(mainCharacter: main_character)
}