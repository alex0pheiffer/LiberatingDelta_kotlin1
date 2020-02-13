package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object

class Scare :
    Card("Scare", R.drawable.card_scare, null, null, false, 0, 0, 20) {

    override val instanceName: String

    override fun preformCard(user: battle_character, target: battle_character) {
        println(target.nom + "has decreased defense ( P ) by 10% for 3 turns.")
        preformEffectTodo(target)
        target.addEffectTodo(this, 2)
    }

    fun preformEffectTodo(target: battle_character) {
        println(target.nom + "has has decreased defense ( P ) by 10%.")
        target.subtractEffect(stats_object(0, 0, 0, 0, 0, 10, 0, 0, 0, 0))
    }

    override val info: () -> String = {
        "↓ Opponent's pDef by 10% for 3 turns.\n" +
        "Targets: " + targetAmt + "\tWeight: " + weight + "\tWait: " + wait
    }
    override fun toString(): String {
        return javaClass.getSimpleName()
    }

    companion object {
        var deckAmt = 0
            private set

        private var numInstance = 0

        fun addDeckAmt() {
            deckAmt++
        }

        fun removeDeckAmt() {
            deckAmt--
        }
    }
    override fun addDeckAmt() { Companion.addDeckAmt()}
    override fun removeDeckAmt() { Companion.removeDeckAmt()}

    init {
        instanceName = this.toString()+ " " + numInstance
        numInstance++
    }
}