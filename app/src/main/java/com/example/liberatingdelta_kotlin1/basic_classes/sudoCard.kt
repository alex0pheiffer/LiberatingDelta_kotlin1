package com.example.liberatingdelta_kotlin1.basic_classes

import java.util.*

class sudoCard(card: Card) {
    var cards: ArrayList<Card>
    fun addCard(card: Card) {
        if (cards.size != 0) {
            if (card.nom == cards[0].nom) { //if card is of the same type as the other cards
                cards.add(card)
            } else {
                throw RuntimeException(
                    "sudoCard mismatch: trying to add " + card.nom + " to " + cards[0].nom
                )
            }
        }
        else {
            cards.add(card)
        }
    }

    fun removeCard(card: Card) {
        var cardExists = false
        if (cards.size != 0) {
            var n = 0
            while (!cardExists && n < cards.size) {
                if (cards[n].instanceName == card.instanceName) {
                    cardExists = true
                    cards.removeAt(n)
                }
                n++
            }
            if (!cardExists) {
                throw RuntimeException("card " + card.instanceName + " doesn't exist in sudocard deck")
            }
        } else {
            throw RuntimeException("cannot remove card " + card.instanceName + " from sudocards when sudocards are empty")
        }
    }

    fun getCard(index: Int): Card {
        return cards[index]
    }

    val size : Int
        get() = cards.size


    init {
        cards = ArrayList()
        addCard(card)
    }
}