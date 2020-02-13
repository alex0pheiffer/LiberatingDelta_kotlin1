package com.example.liberatingdelta_kotlin1.basic_classes

abstract class ExtraQuest(
    name: String,
    startPL: Int,
    region: String,
    city: String,
    drawing: Int,
    phases: Array<phase_objects>,
    //the coorPL is the PL which this EQ gets unlocked.
    var capPL: Int?, //the PL which the EQ is locked
    var objective: String
) : Chapter(name, startPL, region, city, drawing, phases) {

    //when you play the eq, but maybe its not the first time you play it and you dont want the
    //extra dialog from the beginning or whatnot
    fun repeatEQ() {

    }

}