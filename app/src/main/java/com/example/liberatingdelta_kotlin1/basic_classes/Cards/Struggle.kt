package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class Struggle : Card("Struggle", 0, null, null, false, 1, 0, 30) {

    override val instanceName: String

    override fun preformCard(user: battle_character, target: battle_character) {
        println("Returns .3 * pAtk")
        var amt = (user.stats.attackA * .3).toInt()
        amt = target.hitWPhysical(amt)
        println(target.nom+ " lost " + amt + " hp.")
        println(target.nom + " : " + target.hp)
    }

    override val info: () -> String = {
        "Attack Opponent with .3*pAtk.\n" +
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