package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object

class Scare :
    Card("Scare", R.drawable.scare_card, false, false, false, false, 0, 0, 20) {
    val instanceName: String
    fun preformCard(user: battle_character?, target: battle_character) {
        println(target.getNom().toString() + "has decreased defense ( P ) by 10% for 3 turns.")
        preformEffectTodo(target)
        target.addEffectTodo(this, 2)
    }

    fun preformEffectTodo(target: battle_character) {
        println(target.getNom().toString() + "has has decreased defense ( P ) by 10%.")
        target.getEffectStats().subtractStats(stats_object(0, 0, 0, 0, 0, 10, 0, 0, 0, 0))
    }

    val info: String
        get() = "↓ Opponent's pDef by 10% for 3 turns.\n" +
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