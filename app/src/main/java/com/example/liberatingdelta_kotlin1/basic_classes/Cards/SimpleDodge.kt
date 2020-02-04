package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object

class SimpleDodge :
    Card("Simple Dodge", R.drawable.simpledodge_card, false, false, false, false, 0, 0, 50) {
    val instanceName: String
    fun preformCard(user: battle_character, target: battle_character) {
        println(target.getNom().toString() + "has increased evasiveness ( P ) by 40% and ( M ) by 20% for 2 turns.")
        preformEffectTodo(target)
        user.addEffectTodo(this, 1)
    }

    fun preformEffectTodo(target: battle_character) {
        println(target.getNom().toString() + "has increased evasiveness ( P ) by 40% and ( M ) by 20%.")
        target.getEffectStats().addStats(stats_object(0, 0, 0, 4000, 2000, 0, 0, 0, 0, 0))
    }

    val info: String
        get() = "↑ pEva of Self by 40% and mEva by 20% for 2 turns.\n" +
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
        instanceName =
            getClass().getSimpleName().toString() + " " + numInstance
        numInstance++
    }
}