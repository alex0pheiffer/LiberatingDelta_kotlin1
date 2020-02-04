package com.example.liberatingdelta_kotlin1.basic_classes.Cards

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.Characters
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character

class StellaSpark :
    Card("Stella Spark", R.drawable.stellaspark_card, false, true, false, false, 1, 2, 25) {
    val instanceName: String
    fun preformCard(user: battle_character, target: battle_character) {
        println("Returns 1 * mAtk or 0")
        var amt = user.getMatk() as Int
        amt =
            if (user.getType() === Characters.getMType(1)) target.hitWMagic(
                amt,
                user.getType()
            ) else 0
        println(target.getNom().toString() + " lost " + amt + " hp.")
        println(target.getNom().toString() + " : " + target.getHP())
    }

    fun preformEffectTodo(target: battle_character?) { //nothing to do
    }

    val comboSpecific: String
        get() = "Stella"

    val info: String
        get() = "Attack Opponent with 1*pAtk if Self has Fire magic.\n" +
                "Targets: " + getTargetCharAmt() + "\tWeight: " + getWeight() + "\tWait: " + getWait()

    val numInstance: Int

    override fun toString(): String {
        return getClass().getSimpleName()
    }

    companion object {
        /*
    A card made for Stella
     */
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