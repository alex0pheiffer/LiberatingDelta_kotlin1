package com.example.liberatingdelta_kotlin1.basic_classes

abstract class Card(
    val nom: String,
    val cardImg: Int,
    val specWeapon: Weapon?,
    val specCharacter: String?,
    val alliesSupport: Boolean,
    val targetAmt: Int,
    val weight: Int, //how powerful the card is: 0-5
    val wait: Int //"points" the char must wait after card use
)
{
    open val instanceName: String
    var isComboSpec: Boolean = false

    //to be overwritten
    open val info: () -> String? =
        {
            null
        }

    var specCombo: (coupleChar: List<battle_character>, target: battle_character) -> Int =
        {coupleChar: List<battle_character>, target: battle_character ->
            -1 //no combo for the default card
        }
        set(comboFun: (List<battle_character>, battle_character) -> Int ) {
            field = comboFun
            isComboSpec = true
        }

    //should be over written
    open fun preformCard(user: battle_character, target: battle_character): Unit
        {

        }

    //should be overwritten
    open val preformEffectTodo: (target: battle_character) -> Unit =
        {

        }

    override fun toString(): String {
        return this.javaClass.simpleName
    }

    companion object {
        var deckAmt = 0 //number of decks this TYPE of card is in = 0
            private set

        fun addDeckAmt() {
            deckAmt++
        }

        fun removeDeckAmt() {
            deckAmt--
        }
    }

    open fun addDeckAmt() {}
    open fun removeDeckAmt() {}

    init {
        instanceName = this.javaClass.simpleName //instanceName must be overwritten for subclasses!! and concat instanceNum to end.
    }
}