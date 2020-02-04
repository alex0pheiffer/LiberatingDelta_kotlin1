package com.example.liberatingdelta_kotlin1.basic_classes

abstract class inventI(
    val nom: String,
    val use_for_buff: Boolean, //if you have to use the object to get the buff effects, this is TRUE
    val stats: stats_object?
)
{

    override fun toString(): String {
        return "inventI"
    }

}