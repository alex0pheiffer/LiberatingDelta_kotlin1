package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.Characters
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class StellaSpark :
    Card("Stella Spark", R.drawable.card_stellaspark, null, "Stella", false, 1, 2, 25) {

    override val instanceName: String

    override fun preformCard(user: battle_character, target: battle_character) {
        println("Returns 1 * mAtk or 0")
        var amt = user.stats.attackM ?: 0
        amt =
            if (user.type === Characters.magicTypes[1]) target.hitWMagic(amt, user.type)
            else 0
        println(target.nom + " lost " + amt + " hp.")
        println(target.nom + " : " + target.hp)
    }

    override val info: () -> String = {
        "Attack Opponent with 1*pAtk if Self has Fire magic.\n" +
        "Targets: " + targetAmt + "\tWeight: " + weight + "\tWait: " + wait
    }

    override fun toString(): String {
        return javaClass.getSimpleName()
    }

    companion object {
        /*
    A card made for Stella
     */
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