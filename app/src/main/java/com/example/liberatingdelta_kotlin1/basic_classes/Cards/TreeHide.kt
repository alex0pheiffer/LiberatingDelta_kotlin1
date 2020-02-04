package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object

class TreeHide : Card("Tree Hide", 0, false, false, false, false, 1, 0, 50) {
    val instanceName: String
    fun preformCard(user: battle_character, target: battle_character) {
        println(target.getNom().toString() + "has increased evasiveness ( P,M ) by 20% for 3 turns.")
        preformEffectTodo(target)
        user.addEffectTodo(this, 1)
    }

    fun preformEffectTodo(target: battle_character) {
        println(target.getNom().toString() + "has increased evasiveness ( P,M ) by 20%.")
        target.getEffectStats().addStats(stats_object(0, 0, 0, 2000, 2000, 0, 0, 0, 0, 0))
    }

    val info: String
        get() = "↑ Self's mEva and pEva by 20% for 3 turns.\n" +
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