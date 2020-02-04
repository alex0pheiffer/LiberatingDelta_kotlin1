package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.Characters
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class Splash : Card("Splash", 0, false, false, false, false, 5, 0, 100) {
    val instanceName: String
    fun preformCard(user: battle_character, target: battle_character) {
        println("Returns .5 * mAtk or 0")
        var amt = (user.getMatk() * .5) as Int
        amt =
            if (user.getType() !== Characters.getMType(0)) target.hitWMagic(
                amt,
                user.getType()
            ) else 0
        println(target.getNom().toString() + " lost " + amt + " hp.")
        println(target.getNom().toString() + " : " + target.getHP())
    }

    fun preformEffectTodo(target: battle_character?) { //none
    }

    val info: String
        get() = "Attack Opponent with .5*mAtk if Self has magic.\n" +
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