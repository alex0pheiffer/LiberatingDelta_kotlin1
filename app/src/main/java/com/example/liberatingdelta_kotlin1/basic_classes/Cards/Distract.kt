package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class Distract :
    Card("Distract", R.drawable.distract_card, false, false, false, false, 1, 2, 10) {
    val instanceName: String
    fun preformCard(user: battle_character?, target: battle_character) {
        println(target.getNom().toString() + " has lost a ( 1 ) turn!")
        target.addEffectTodo(this, 1)
    }

    fun preformEffectTodo(target: battle_character) {
        println("Turn is skipped.")
        target.setTurnSkip(true)
    }

    val info: String
        get() = "Opponent will lose a turn.\n" +
                "Targets: " + getTargetCharAmt() + "\tWeight: " + getWeight() + "\tWait: " + getWait()

    val numInstance: Int

    override fun toString(): String {
        return getClass().getSimpleName()
    }

    companion object {
        var deckAmt = 0
            private set
        private const val numInstance = 0

        fun addDeckAmt() {
            deckAmt++
        }

        fun removeDeckAmt() {
            deckAmt--
        }
    }

    init {
        instanceName = getClass().getSimpleName().toString() + " " + numInstance
        numInstance++
    }
}