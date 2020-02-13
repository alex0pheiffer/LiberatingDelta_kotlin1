package com.example.liberatingdelta_kotlin1.basic_classes


//for dialog

abstract class Phase(pl: Int, phase: Int) : phase_objects(pl, phase) {
    var dialog: Array<String>? = null
    var characters: Array<Characters>? = null
    var img_type: Array<Int>? = null //for the static character's image type
    var background: Array<Int>? = null //because we put id's in here

    override fun toString(): String {
        return "Phase ${super.pl} : ${super.phase}"
    }
}