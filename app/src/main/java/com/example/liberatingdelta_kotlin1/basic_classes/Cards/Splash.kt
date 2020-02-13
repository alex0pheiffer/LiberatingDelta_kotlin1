package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.Characters
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class Splash : Card("Splash", 0, null, null, false,5, 0, 100) {

    override val instanceName: String

    override fun preformCard(user: battle_character, target: battle_character) {
        println("Returns .5 * mAtk or 0")
        var amt = (user.stats.attackM?.times(.5) ?: 0) as Int
        amt =
            if (user.type !== Characters.magicTypes[0]) target.hitWMagic(amt, user.type)
            else 0
        println(target.nom + " lost " + amt + " hp.")
        println(target.nom + " : " + target.hp)
    }

    override val info: () -> String = {
        "Attack Opponent with .5*mAtk if Self has magic.\n" +
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
        instanceName = javaClass.getSimpleName()+ " " + numInstance
        numInstance++
    }
}