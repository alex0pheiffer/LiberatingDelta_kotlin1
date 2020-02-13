package com.example.liberatingdelta_kotlin1.basic_classes

//to be extended by fights and phases
abstract class phase_objects(val pl: Int, val phase: Int)

//PHASES START AT 0
{
    override fun toString(): String {
        return javaClass.simpleName
    }

}