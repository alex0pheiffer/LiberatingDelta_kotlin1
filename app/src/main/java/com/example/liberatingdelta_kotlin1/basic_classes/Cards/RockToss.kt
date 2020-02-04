package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class RockToss :
    Card("Rock Toss", R.drawable.rocktoss_card, false, false, false, false, 1, 0, 50) {
    val instanceName: String
    fun preformCard(user: battle_character, target: battle_character) {
        println("Returns .7 * aAtk")
        var amt = (user.getPatk() * .7) as Int
        amt = target.hitWPhysical(amt)
        println(target.getNom().toString() + " lost " + amt + " hp.")
        println(target.getNom().toString() + " : " + target.getHP())
    }

    fun preformEffectTodo(target: battle_character?) { //none
    }

    val numInstance: Int

    val info: String
        get() = "Attack Opponent with .7*pAtk.\n" +
                "Targets: " + getTargetCharAmt() + "\tWeight: " + getWeight() + "\tWait: " + getWait()

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