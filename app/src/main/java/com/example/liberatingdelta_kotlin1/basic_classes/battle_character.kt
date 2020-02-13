package com.example.liberatingdelta_kotlin1.basic_classes

import java.lang.RuntimeException
import java.util.*

class battle_character {
    val isMC: Boolean
    val isAlly: Boolean
    private val thisCharacter: fighting_character?
    var itemEquipStats: stats_object?
    var weaponEquipStats: stats_object?
    private val fullDeck: Deck
    var remainingDeck: Deck
    var hand: ArrayList<Card?>?
    var hp: Int = 0
        private set
    var wait: Int =0
    var cardEffectTodo: ArrayList<Card>? = null
    var waitEffectTodo: ArrayList<Int>? = null
    var isDead = false
    var turnSkip = false
    val statsStatic: stats_object
    var effectStats: stats_object
        private set
    val magicalAffinity: Int
    val strength: Int
    var stats: stats_object
        private set

    constructor(thisCharacter: fighting_character, timeToTurn: Int, ally: Boolean) {
        this.thisCharacter = thisCharacter
        hp = thisCharacter.stats.health
        isMC = main_character::class.java.isAssignableFrom(thisCharacter.javaClass)
        isAlly = ally
        itemEquipStats = thisCharacter.itemStats
        weaponEquipStats = thisCharacter.weaponStats
        fullDeck = thisCharacter.deck!!.copy()
        remainingDeck = fullDeck.copy()
        hand = ArrayList()
        wait = timeToTurn
        weightPoints = 6
        cardEffectTodo = ArrayList()
        waitEffectTodo = ArrayList()
        isDead = false
        turnSkip = false
        statsStatic = thisCharacter.stats
        effectStats = stats_object(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        stats = statsStatic+effectStats
        magicalAffinity=thisCharacter.magicalAffinity
        strength=thisCharacter.strength
        stats.setAttackM(magicalAffinity,strength)
    }

    //for creating temporary battle characters, for times such as simulating results
    constructor(battler: battle_character) {
        isMC = battler.isMC
        isAlly = battler.isAlly
        isDead = battler.isDead
        thisCharacter = battler.getThisCharacter()
        itemEquipStats = battler.itemEquipStats
        weaponEquipStats = battler.weaponEquipStats
        fullDeck = battler.getFullDeck().copy()
        remainingDeck = battler.remainingDeck.copy()
        hand = ArrayList()
        for (i in 0 until battler.getHand()) {
            hand!!.add(battler.getHandCard(i))
        }
        hp = battler.hp
        wait = battler.wait
        weightPoints = battler.weightPoints
        statsStatic = battler.statsStatic
        effectStats = stats_object(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        stats = statsStatic+effectStats
        magicalAffinity=battler.magicalAffinity
        strength=battler.strength
        stats.setAttackM(magicalAffinity,strength)
    }

    fun reduceWait(reduction: Int): Int {
        wait -= reduction
        return wait
    }

    fun increaseWait(increase: Int): Int {
        wait += increase
        return wait
    }

    var weightPoints: Int = 0
        set(pts) {
            //max weight is 10
            if (pts > 10) field = 10
            else field = pts
        }

    //volatility is stored in 100s (1000 == 10%)
    val isVolatile: Boolean
        get() { //volatility is stored in 100s (1000 == 10%)
            val vol = (stats.volatility) / 100
            val rand = (Math.random() * 100).toInt()
            return if (vol >= rand) true else false
        }

    //returns the amt of damage dealt
    fun hitWMagic(amt: Int, type: String): Int { //when the attack is purely magical
        var change = 0
        if (type == Characters.magicTypes[1]) { //Fire
            change = (stats.fireDefense * amt / 100.0).toInt()
        } else if (type == Characters.magicTypes[2]) { //Water
            change = (stats.waterDefense * amt / 100.0).toInt()
        } else if (type == Characters.magicTypes[3]) { //Land
            change = (stats.landDefense * amt / 100.0).toInt()
        } else if (type == Characters.magicTypes[4]) { //Air
            change = (stats.airDefense * amt / 100.0).toInt()
        } else if (type == Characters.magicTypes[5]) { //Scatter / ???
            val def = (stats.fireDefense+stats.landDefense+stats.waterDefense+stats.airDefense) / 4.0
            change = (def * amt / 100.0).toInt()
        }
        reduceHealth(change)
        return change
    }

    //returns the amt of damage dealt
    fun hitWPhysical(amt: Int): Int { //when the attack is purely physical
        val change = ( stats.aDefense * amt / 100.0).toInt()
        reduceHealth(change)
        return change
    }

    //returns the amt of damage dealt
    fun hitWBoth(amt: Int, percentM: Int, type: String): Int
    {
        //when the attack is both magical and physical... ex a magic sword
        val Mhit = (amt * percentM / 100.0).toInt()
        val Ahit = amt - Mhit
        return hitWMagic(Mhit, type) + hitWPhysical(Ahit)
    }

    private fun reduceHealth(amt: Int) {
        if (hp - amt <= 0) {
            hp = 0
            isDead = true
        } else {
            hp = hp - amt
        }
    }

    fun removeWeapon() {
        thisCharacter?.weapon = null
        weaponEquipStats = null
    }

    fun removeItem() {
        thisCharacter?.item = null
        itemEquipStats = null
    }

    fun shuffleDeck() {
        remainingDeck.shuffle()
    }

    fun resetDeck() {
        remainingDeck = fullDeck.copy()
        shuffleDeck()
    }

    //DOES NOT put the card in your hand
    fun drawCard(): Card? {
        if (remainingDeck.cardAmt == 0) return null
        val pulled: Card? =
            remainingDeck.getCard(remainingDeck.cardAmt - 1)
        if (pulled != null) remainingDeck.removeCard(pulled)
        return pulled
    }

    //adds a given card to the user's deck
    //this shuffles the deck..
    fun addCardToDeck(card: Card?) {
        remainingDeck.addCard(card)
        shuffleDeck()
    }

    fun drawCardToHand() {
        val tempCard: Card?
        if (hand?.size ?: 0 < 5) {
            tempCard = drawCard()
            if (tempCard != null) {
                hand?.add(tempCard)
            }
        }
    }

    fun getHand(): Int {
        return hand?.size ?: 0
    }

    fun getHandCard(index: Int): Card? {
        return if (index < hand?.size ?: 0) {
            hand!![index]
        } else null
    }

    //a version of useCard that does not remove the card...
    fun testCard(card: Card, target: battle_character): Boolean {
        card.preformCard(this, target)
        return if (target.isDead) true else false
    }

    //returns if the target was killed
    fun useCard(card: Card, target: battle_character): Boolean {
        card.preformCard(this, target)
        hand?.remove(card)
        drawCardToHand()
        return if (target.isDead) true else false
    }

    fun addEffectTodo(effect: Card, turnDuration: Int) {
        if (effect.preformEffectTodo != null) {
            cardEffectTodo!!.add(effect)
            waitEffectTodo!!.add(turnDuration)
        }
        else throw RuntimeException("ERROR: adding an effect when the card has no effect created")
    }

    fun applyEffectTodo() {
        resetEffectStats()
        println("applying " + cardEffectTodo!!.size + " effects...")
        var i = 0
        while (i < cardEffectTodo!!.size) {
            //these effects are using the total stats...the effect stats of the previous round and the static stats

            println("effect : " + cardEffectTodo!![i].nom)
            cardEffectTodo!![i].preformEffectTodo(this)
            waitEffectTodo!![i] = waitEffectTodo!![i] - 1
            if (waitEffectTodo!![i] == 0) {
                cardEffectTodo!!.removeAt(i)
                waitEffectTodo!!.removeAt(i)
                i--
            }
            i++
        }
        //todo do you want to apply the sum effects to the stats at the end...
        //todo or rather change the stats in the add/subtract effectStats function?
        stats = statsStatic+effectStats
    }

    fun resetEffectStats() {
        effectStats.health = 0
        effectStats.evasiveA = 0
        effectStats.evasiveM = 0
        effectStats.attackA = 0
        effectStats.aDefense = 0
        effectStats.fireDefense = 0
        effectStats.waterDefense = 0
        effectStats.airDefense = 0
        effectStats.landDefense = 0
        effectStats.volatility = 0
    }

    fun subtractEffect(subtract: stats_object) {
        effectStats.subtractStats(subtract)
    }

    fun addEffect(add: stats_object) {
        effectStats.addStats(add)
    }

    fun fillHand() {
        println("Deck of " + nom + " : " + remainingDeck.nom)
        for (i in 0 until remainingDeck.sudoCardAmt) {
            println(
                remainingDeck.getSudoCard(i)?.getCard(0)?.nom + " " + remainingDeck.getSudoCard(i)?.size
            )
        }
        while (hand?.size ?: 0 < 5) {
            drawCardToHand()
        }
    }

    fun clearHand() {
        for (i in hand?.indices ?: 0..0) {
            hand!![i] = null
        }
    }

    fun getFullDeck(): Deck {
        return fullDeck
    }

    val nom: String
        get() = thisCharacter?.name ?: ""

    fun getThisCharacter(): fighting_character? {
        return thisCharacter
    }

    val type: String
        get() = thisCharacter?.magicType ?: ""
}