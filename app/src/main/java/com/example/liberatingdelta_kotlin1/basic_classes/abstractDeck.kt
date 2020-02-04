package com.example.liberatingdelta_kotlin1.basic_classes

interface abstractDeck {
    //returns the deck's name
    val nom: String?

    //only sorts the very last card...
    fun alphabetizeDeck(): Int

    //typical binary search: starting input... card, 0, alphabetcards.size()-1
    fun binarySearch(card: Card?, startIndex: Int, endIndex: Int): Int

    fun numCardInstance(card: Card?): Int
    //when bringing a new card into the deck  ---- returns the new index of the sudoCard, will usually be ignored
    fun addCard(card: Card?): Int

    //todo replace with a mitigation...pop up window...
//todo also probably check that the decksize isn't full before beginning a mission...
//when removing a card from your inventory...you will no longer own this card
    fun removeCard(card: Card?)

    //for accessing the allCards list...
    fun getCard(index: Int): Card?

    //note: when you add a new card, it will give you an index... but it will change if you add another card so be careful
    fun getSudoCard(index: Int): sudoCard?

    fun getSudoCard(card: Card?): sudoCard?
    //returns the amount of sudocard bundles
    val sudoCardAmt: Int

    fun getSudoCardIndex(card: Card?): Int
    //updates the total amount individual cards: cardAmt
    val cardAmt: Int
}