package com.example.liberatingdelta_kotlin1.basic_classes

import android.util.Log
import java.util.*

class Deck : abstractDeck {
    override var nom: String
        private set
    var instanceName: String
        private set
    private var alphabetcards: ArrayList<sudoCard>
    private var allCards: ArrayList<Card?>//this is in no particular order. its just a list of all the cards.
    override val cardAmt : Int //total number of cards in the deck
        get() = allCards.size
    var totalWeight = 0
        private set
    var charequip: String?//what char can validly hold this deck
        private set
    var charHolding: String? = null //what character __has__ this deck equipped?
    var isValid = false
        private set
    var isWeaponSpec = false
        private set
    private var specificWeapons: ArrayList<specWeapon>? = null //the weapons that cards may refer to in this deck

    private inner class specWeapon(val weapon: Weapon) {
        var cardAmt = 1
            private set
        fun addCard() {
            cardAmt++
        }
        fun removeCard(): Int {
            cardAmt--
            return cardAmt
        }
    }

    private val MAX_CARD_AMT = 5
    private val MAX_DECK_SIZE = 30
    private val MAX_WEIGHT = 50
    private val charequipers = arrayOf("All", "Vivian", "Katherine", "Delta", "Gamma", "Invalid")

    //usually for .copy()
    constructor(
        name: String,
        charHolding: String?,
        cards: ArrayList<sudoCard>
    ) {
        nom = name
        alphabetcards = ArrayList()
        allCards = ArrayList()
            this.charHolding = charHolding
        charequip = charequipers[0]
        numInstance++
        instanceName = javaClass.simpleName + " " + numInstance
        for (i in cards.indices) {
            for (j in 0 until cards[i].size) {
                addCard(cards[i].getCard(j))
            }
        }
        resetTotalWeight()
    }

    constructor(name: String, charHolding: String?) {
        nom = name
        alphabetcards = ArrayList()
        allCards =
            ArrayList()
        if (charHolding != null) {
            this.charHolding = charHolding
        }
        charequip = charequipers[0]
        numInstance++
        instanceName = javaClass.simpleName + " " + numInstance
    }

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    private fun binarySearch(cardName: String, startIndex: Int, endIndex: Int): Int {
        if (endIndex >= startIndex) {
            val mid = startIndex + ((endIndex - startIndex).toDouble() / 2 + .5).toInt()
            // If the element is present at the middle itself
            if (alphabetcards[mid].getCard(0).toString().compareTo(cardName) == 0) return mid
            // If element is smaller than mid, then it can only be present in left subarray
            return if (alphabetcards[mid].getCard(0).toString().compareTo(cardName) > 0) binarySearch(
                cardName,
                startIndex,
                mid - 1
            ) else binarySearch(cardName, mid + 1, endIndex)
            // Else the element can only be present in right subarray
        }
        // We reach here when element is not present in array
        return -1
    }

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    override fun binarySearch(card: Card?, startIndex: Int, endIndex: Int): Int {
        val x:String = card?.toString() ?: ""
        //System.out.println("--- bi : "+card.toString()+" ---");
        //System.out.println("s: "+startIndex+"\te:"+endIndex);
        if (endIndex >= startIndex) {
            val mid = startIndex + ((endIndex - startIndex).toDouble() / 2 + .5).toInt()
            //System.out.println("mid: "+mid);
            // If the element is present at the middle itself
            if (alphabetcards[mid].getCard(0).toString().compareTo(x) == 0) { //System.out.println("is mid : "+alphabetcards.get(mid).getCard(0).toString());
                return mid
            }
            // If element is smaller than mid, then it can only be present in left subarray
            return if (alphabetcards[mid].getCard(0).toString().compareTo(x) > 0) { //System.out.println("is smaller : "+alphabetcards.get(mid).getCard(0).toString());
                binarySearch(card, startIndex, mid - 1)
            } else binarySearch(card, mid + 1, endIndex)
            // Else the element can only be present in right subarray
            //System.out.println("is larger: "+alphabetcards.get(mid).getCard(0).toString());
        }
        // We reach here when element is not present in array
        return -1
    }

    //only sorts the very last card...
    override fun alphabetizeDeck(): Int {
        val i = alphabetcards.size - 1
        var newIndex = 0
        var j = i
        var stillLarger = true
        while (stillLarger) {
            j--
            if (alphabetcards[j].getCard(0).toString().compareTo(alphabetcards[i].getCard(0).toString()) <= 0) {
                stillLarger = false
                newIndex = j + 1
            } else if (j == 0) {
                stillLarger = false
                newIndex = 0
            }
        }
        alphabetcards.add(newIndex, alphabetcards[i])
        alphabetcards.removeAt(i + 1)
        return newIndex
    }

    private fun checkCharEquip(): String {
        var belongs = charequipers[0]
        for (card in alphabetcards) {
            if (card.getCard(0).specCharacter != null) {
                if (belongs != card.getCard(0).specCharacter) {
                    if (belongs == charequipers[0]) {
                        belongs = card.getCard(0).specCharacter!!
                    } else {
                        return charequipers[5]
                    }
                }
            }
        }
        return belongs
    }

    private fun checkCharEquip(exceptionCard: Card?): String? {
        val index = binarySearch(exceptionCard, 0, alphabetcards.size - 1)
        return if (index == -1) {
            throw RuntimeException("exception card DNE")
        } else if (alphabetcards[index].size > 1) { //the card being removed wont change the charEquip
            charequip
        } else {
            var belongs = charequipers[0]
            for (card in alphabetcards) {
                if (card.getCard(0).instanceName != exceptionCard!!.instanceName) {
                    if (card.getCard(0).specCharacter != null) {
                        if (belongs != card.getCard(0).specCharacter!!) {
                            if (belongs == charequipers[0]) {
                                belongs = card.getCard(0).specCharacter!!
                            } else {
                                return charequipers[5]
                            }
                        }
                    }
                }
            }
            belongs
        }
    }

    private fun updateCharEquip(card: Card?, adding: Boolean) {
        if (card?.specCharacter != null) {
            Log.d("TEST",card.nom)
            if (adding && charequip != card.specCharacter) {
                if (charequip == charequipers[0]) {
                    charequip = card.specCharacter
                }
                else {
                    charequip = charequipers[5]
                }
            }
            if (!adding && charequip == card.specCharacter) {
                charequip = if (charequip == charequipers[0]) {
                    throw RuntimeException("Card is CharSpec but Deck is not")
                }
                else {
                    checkCharEquip(card)
                }
            }
            else if (!adding && charequip == charequipers[5]) {
                charequip = checkCharEquip(card)
            }
        }
    }

    private fun checkWeaponSpec(): Boolean {
        for (card in alphabetcards) {
            if (card.getCard(0).specWeapon != null) return true
        }
        return false
    }

    override fun numCardInstance(card: Card?): Int {
        val index = binarySearch(card, 0, alphabetcards.size - 1)
        return if (index != -1) {
            alphabetcards[index].size
        } else {
            0
        }
    }

    private fun addWeaponSpec(weep: Weapon) {
        if (specificWeapons == null) {
            specificWeapons = ArrayList()
            specificWeapons!!.add(specWeapon(weep))
        }
        else {
            var bool = false
            for (a in specificWeapons!!) {
                if (a.weapon.nom == weep.nom) {
                    bool = true
                    a.addCard()
                }
            }
            if (!bool) {
                specificWeapons!!.add(specWeapon(weep))
            }
        }
    }

    private fun removeWeaponSpec(weep: Weapon) {
        if (specificWeapons == null) {
            throw RuntimeException("Removing " + weep.nom + " from non-weapon-specific deck")
        }
        else {
            var bool = false
            var index = 0
            for (a in specificWeapons!!) {
                if (a.weapon.nom == weep.nom) {
                    bool = true
                    a.removeCard()
                    if (a.cardAmt == 0) {
                        specificWeapons!!.removeAt(index)
                    }
                    break;
                }
                index++
            }
            if (!bool) {
                throw RuntimeException("Card's weapon DNE")
            }
        }
    }

    //when bringing a new card into the deck
    override fun addCard(card: Card?): Int { //update the charequip if its not already invalid
        if (card == null) return -1
        Log.d("TEST",charequip +" "+charequipers[5])
        if (charequip != charequipers[5]) updateCharEquip(card, true)
        val cardinst = numCardInstance(card)
        if (cardinst == 0) {
            card.addDeckAmt()
        }
        //if the deck isnt invalid, find out if this card makes it invalid
        if (isValid) {
            if (charequip == charequipers[5]) isValid = false
            if (isValid && cardAmt + 1 > MAX_DECK_SIZE) {
                isValid = false
            }
            if (isValid && totalWeight + card.weight > MAX_WEIGHT) {
                isValid = false
            }
            if (isValid && cardinst >= MAX_CARD_AMT) isValid = false
        }
        if (card.specWeapon != null) {
            val cardWeapon: Weapon = card.specWeapon
            if (isValid && isWeaponSpec) {
                if (cardWeapon.charEquip != charequip) isValid = false
            }
            else if (isValid) { //all characters -> char of this weapon
                //this should be redundant because the card's charequip should take care of this
                if (charequip == charequipers[0]) {
                    charequip = cardWeapon.charEquip
                    throw RuntimeException("card's charequip is all but it's specific weapon is char-spec: " + card.nom)
                }
                else if (!(card.specWeapon?.charEquip?.equals(charequip) ?: false)) {
                    isValid = false
                }
                isWeaponSpec = true
            }
            addWeaponSpec(cardWeapon)
        }
        totalWeight += card.weight
        addToSudoDeck(card)
        allCards.add(card)
        return 0
    }

    //when removing a card from the deck
    override fun removeCard(card: Card?) {
        val numinst = numCardInstance(card)
        if (numinst == 1) {
            card!!.removeDeckAmt()
        }
        if (card!!.specWeapon != null) {
            removeWeaponSpec(card.specWeapon!!)
        }
        if (card.specCharacter != null) {
            updateCharEquip(card, false)
        }
        if (charequip == charequipers[4]) {
            isValid = false
        }
        if (isValid && cardAmt > MAX_DECK_SIZE) {
            isValid = false
        }
        if (isValid && totalWeight > MAX_WEIGHT) {
            isValid = false
        }
        if (isValid && numinst - 1 > MAX_CARD_AMT) {
            isValid = false
        }
        removeFromSudoDeck(card)
        allCards.remove(card)
        totalWeight -= card.weight
    }

    private fun addToSudoDeck(card: Card?) { //check to make sure the deck isn't empty
        var newIndex = -1
        if (alphabetcards.size == 0) {
            alphabetcards.add(sudoCard(card!!))
            newIndex = 0
        } else { //try to find the sudocard deck for this card
            val index = binarySearch(card, 0, alphabetcards.size - 1)
            if (index != -1) {
                alphabetcards[index].addCard(card!!)
                newIndex = index
            } else {
                alphabetcards.add(sudoCard(card!!))
                alphabetizeDeck()
                //throw new RuntimeException("CHECK!!");
            }
        }
    }

    //when removing a card from your inventory...you will no longer own this card
    private fun removeFromSudoDeck(card: Card?) { //System.out.println("_________");
        //System.out.println(card.toString());
        //System.out.println("_________");
        /*
        for (int i = 0; i < alphabetcards.size(); i++) {
            for (int j = 0; j < alphabetcards.get(i).getAmount(); j++) {
                System.out.println(alphabetcards.get(i).getCard(j).toString());
            }
        }
        */
        val index = binarySearch(card, 0, alphabetcards.size - 1)
        //find the sudocard that matches this card
        //System.out.println("index: "+index);
        if (index == -1) {
            throw RuntimeException("error: card " + card!!.nom + " DNE in allCards")
        }
        else {
            if (alphabetcards[index].size < 2) { //there is only one (or 0) instances of this card in the deck...remove the sudoCard
                alphabetcards.removeAt(index)
            } else {
                var cardExists = false
                for (i in 0 until alphabetcards[index].size) {
                    if (alphabetcards[index].getCard(i).instanceName == card!!.instanceName) { //the card is the same as this card
                        alphabetcards[index].removeCard(card)
                        cardExists = true
                    }
                }
                if (!cardExists) {
                    throw RuntimeException("card instance " + card!!.instanceName + " DNE in it's sudocard")
                }
            }
        }
    }

    fun getSudoCard(name: String): sudoCard {
        val index = binarySearch(name, 0, alphabetcards.size - 1)
        return alphabetcards[index]
    }

    //for accessing the allCards list...
    override fun getCard(index: Int): Card? {
        return allCards[index]
    }

    //note: when you add a new card, it will give you an index... but it will change if you add another card so be careful
    override fun getSudoCard(index: Int): sudoCard? {
        return alphabetcards[index]
    }

    override fun getSudoCard(card: Card?): sudoCard? {
        val index = binarySearch(card, 0, alphabetcards.size - 1)
        return if (index != -1) {
            alphabetcards[index]
        } else null
    }

    //returns the amount of sudocard bundles
    override val sudoCardAmt: Int
        get() = alphabetcards.size

    override fun getSudoCardIndex(card: Card?): Int {
        return binarySearch(card, 0, alphabetcards.size - 1)
    }

    fun getGeneralCard(index: Int): Card {
        return alphabetcards[index].getCard(0)
    }

    fun getGeneralCard(name: String): Card {
        val index = binarySearch(name, 0, alphabetcards.size - 1)
        return alphabetcards[index].getCard(0)
    }

    fun removeLastCard(name: String) {
        val tempSudo = getSudoCard(name)
        removeCard(tempSudo.getCard(tempSudo.size - 1))
    }

    //take note that this function doesnt clear allCards nor alphabetCards
    fun removeAll() {
        var temp: sudoCard
        for (n in alphabetcards.indices) {
            temp = alphabetcards[n]
            //int numinst = numCardInstance(temp);
            // if (numinst == 1) {
            temp.getCard(0).removeDeckAmt()
            //}
        }
    }

    //shuffles the allCards list
    fun shuffle() {
        var tempCard: Card?
        for (i in allCards.indices) {
            var newIndex = (Math.random() * allCards.size).toInt()
            tempCard = allCards[newIndex]
            allCards[newIndex] = allCards[i]
            allCards[i] = tempCard
        }
    }

    fun resetTotalWeight(): Unit {
        var total = 0
        for (n in alphabetcards.indices) {
            total += alphabetcards[n].getCard(0).weight * alphabetcards[n].size
        }
        totalWeight = total
    }

    fun copy(): Deck {
        return Deck(nom + "_c", charHolding, alphabetcards)
    }

    override fun toString(): String {
        return javaClass.simpleName
    }

    companion object {
        //when moving a card in the deck to a differnet position in the deck
        //public boolean moveCard(Card card, int location) {
        //    boolean moved = true; }
        var numInstance = 0
            private set
    }
}