package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class Shove :
    Card("Shove", 0, null, null, false, 1, 0, 30) {
    override val instanceName: String

    override fun preformCard(user: battle_character, target: battle_character) {
        println("Returns .4 * pAtk")
        var amt = (user.Patk * .4) as Int
        amt = target.hitWPhysical(amt)
        println(target.nom.toString() + " lost " + amt + " hp.")
        println(target.nom.toString() + " : " + target.hp)
    }

    override val info: () -> String = {
        "Attack Opponent with .4*pAtk.\n" +
                "Targets: " + targetAmt + "\tWeight: " + weight + "\tWait: " + wait
    }

    override fun toString(): String {
        return javaClass.getSimpleName()
    }

    companion object {
        var deckAmt = 0
            private set

        var numInstance = 0

        fun addDeckAmt() {
            deckAmt++
        }

        fun removeDeckAmt() {
            deckAmt--
        }
    }

    init {
        instanceName = this.toString()+ " " + numInstance
        numInstance++
    }
}