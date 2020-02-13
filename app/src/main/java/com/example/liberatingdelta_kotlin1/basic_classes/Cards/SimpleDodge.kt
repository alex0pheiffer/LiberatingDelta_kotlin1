package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character
import com.example.liberatingdelta_kotlin1.basic_classes.stats_object

class SimpleDodge :
    Card("Simple Dodge", R.drawable.card_simpledodge, null, null, false, 0, 0, 50) {

    override val instanceName: String

    override fun preformCard(user: battle_character, target: battle_character) {
        println(target.nom + "has increased evasiveness ( P ) by 40% and ( M ) by 20% for 2 turns.")
        preformEffectTodo(target)
        user.addEffectTodo(this, 1)
    }

    fun preformEffectTodo(target: battle_character) {
        println(target.nom + "has increased evasiveness ( P ) by 40% and ( M ) by 20%.")
        target.addEffect(stats_object(0, 0, 0, 4000, 2000, 0, 0, 0, 0, 0))
    }

    override val info: () -> String = {
        "↑ pEva of Self by 40% and mEva by 20% for 2 turns.\n" +
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
        instanceName =
            javaClass.getSimpleName() + " " + numInstance
        numInstance++
    }
}