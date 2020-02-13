package com.example.liberatingdelta_kotlin1.basic_classes

import com.example.liberatingdelta_kotlin1.basic_classes.sudoCard
import java.util.*

class BlankDeck : abstractDeck {
    override val nom = "BlankDeck"
    var alphabetcards: ArrayList<sudoCard>
    var allCards: ArrayList<Card?> //this is in no particular order. its just a list of all the cards.
    override val cardAmt: Int
        get() = allCards.size
    private val MAX_DECK_SIZE = 200

    //only sorts the very last card...
    override fun alphabetizeDeck(): Int {
        var stillLarger = true
        val i = alphabetcards.size - 1
        var newIndex = 0
        var j = i
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

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    override fun binarySearch(card: Card?, startIndex: Int, endIndex: Int): Int {
        if (card == null) return -1
        val x = card.toString()
        if (endIndex >= startIndex) {
            val mid = startIndex + ((endIndex - startIndex).toDouble() / 2 + .5).toInt()
            // If the element is present at the middle itself
            if (alphabetcards[mid].getCard(0).toString().compareTo(x) == 0) return mid
            // If element is smaller than mid, then it can only be present in left subarray
            return if (alphabetcards[mid].getCard(0).toString().compareTo(x) > 0) binarySearch(card, startIndex, mid - 1) else binarySearch(card, mid + 1, endIndex)
            // Else the element can only be present in right subarray
        }
        // We reach here when element is not present in array
        return -1
    }

    override fun numCardInstance(card: Card?): Int {
        val index = binarySearch(card, 0, alphabetcards.size - 1)
        return if (index != -1) {
            alphabetcards[index].size
        } else {
            0
        }
    }

    //will be overwritten by real decks
    //when bringing a new card into the deck  ---- returns the new index of the sudoCard, will usually be ignored
    override fun addCard(card: Card?): Int {
        //todo replace with a mitigation...pop up window...
        //todo also probably check that the decksize isn't full before beginning a mission...
        if (cardAmt+1 >= MAX_DECK_SIZE) throw RuntimeException("adding too many cards...")
        //check to make sure the deck isn't empty
        var newIndex = if (alphabetcards.size == 0) {
            alphabetcards.add(sudoCard(card!!))
            0
        } else { //try to find the sudocard deck for this card
            val index = binarySearch(card, 0, alphabetcards.size - 1)
            if (index != -1) {
                alphabetcards[index].addCard(card!!)
                index
            } else {
                alphabetcards.add(sudoCard(card!!))
                alphabetizeDeck()
                //throw new RuntimeException("CHECK!!");
            }
        }
        allCards.add(card)
        return newIndex
    }

    //will be overwritten by real decks
    //when removing a card from your inventory...you will no longer own this card
    override fun removeCard(card: Card?) {
        val index = binarySearch(card, 0, alphabetcards.size - 1)
        //find the sudocard that matches this card
        if (index == -1) {
            throw RuntimeException("error: card " + card!!.nom + " DNE in allCards")
        } else {
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
        allCards.remove(card)
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

    //only applicable to BlankDeck
    //updates the total amount individual cards: cardAmt
    fun updateCardAmt() {
        var amt = 0
        for (i in alphabetcards.indices) {
            amt = amt + alphabetcards[i].size
        }
    }

    override fun toString(): String {
        return javaClass.simpleName
    }

    init {
        alphabetcards = ArrayList()
        allCards = ArrayList()
    }
}