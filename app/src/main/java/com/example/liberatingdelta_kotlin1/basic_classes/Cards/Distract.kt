package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class Distract :
    Card("Distract", R.drawable.card_distract, null, null, false,1, 2, 10) {

    override val instanceName: String

    override fun preformCard(user: battle_character, target: battle_character) {
        println(target.nom + " has lost a ( 1 ) turn!")
        target.addEffectTodo(this, 1)
    }

    fun preformEffectTodo(target: battle_character) {
        println("Turn is skipped.")
        target.turnSkip = true
    }

    override val info: () -> String = {
        "Opponent will lose a turn.\n" +
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
        instanceName = javaClass.getSimpleName() + " " + numInstance
        numInstance++
    }
}