package com.example.liberatingdelta_kotlin1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.liberatingdelta_kotlin1.MainMenyuFragments.*
import com.example.liberatingdelta_kotlin1.actFragments.MainMenyuFragment
import com.example.liberatingdelta_kotlin1.basic_classes.*
import com.example.liberatingdelta_kotlin1.basic_classes.the_MCs.Katherine
import com.example.liberatingdelta_kotlin1.databinding.ActivityMainmenyuBinding
import com.example.liberatingdelta_kotlin1.db_files.*
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.Veneland
import kotlinx.android.synthetic.main.fragment_main_menyu.*
import java.util.*

class MainMenyuActivity : AppCompatActivity(), UpButtonFragment.upButtonListener, MMCFragment.mmcFragmentListener,
    MainMenyuFragment.mainMenyuFragmentListener, characterViewInterfaceOut{

    var pl : Int = 0
    lateinit var this_pl : PL
    var regionFragments = arrayOfNulls<Fragment?>(17) //list of all my region fragments, per the PL

    private lateinit var rpgViewModel: RPG_ViewModel
    private var updateUserValuesCounter = 0
    private var updateUserEQPlayedCounter = 0
    private var updateUserCharactersCounter = 0
    private var updateUserCardsCounter = 0
    private var updateUserDecksCounter = 0
    private var updateUserInventoryCounter = 0
    //todo: check the max through the db...#rows
    private val MAX_INVENTORY = 100

    private inner class dataController internal constructor() {
        //combination of checkUserData and userInventory in order to prevent utmost confusion...
        //mainCharacter data storage...
        private var KatieLevel = 0
        private var VivLevel = 0
        private var DeltaLevel = 0

        private inner class checkUserData {
            private var lValues: List<User_Values>? = null
            private var lEQPlayed: List<User_EQPlayed>? = null
            private var lCharacters: List<User_Characters>? = null
            private var lCards: List<User_Cards>? = null
            private var lDecks: List<User_Decks>? = null
            private var lInventory: List<User_Inventory>? = null
            private var deckNameCards: List<User_Cards>? = null
            private var cur_region: regions
            fun setlValues(vals: List<User_Values>?) {
                lValues = vals
                if (pl < 3) {
                    pl = pL
                    this_pl = PL_VendingMachine.getPL(pl)
                } else if (lValues!![0].cur_PL != pl) { //update our pl, it has changed
                    pl = lValues!![0].cur_PL
                    this_pl = PL_VendingMachine.getPL(pl)
                }
                cur_region = currentRegion ?: this_pl.getRegion("Veneland")!!
            }

            fun setlCharacters(lCharacters: List<User_Characters>?) {
                this.lCharacters = lCharacters
            }

            fun setlEQPlayed(lEQPlayed: List<User_EQPlayed>?) {
                this.lEQPlayed = lEQPlayed
            }

            fun setlCards(vals: List<User_Cards>?) {
                lCards = vals
            }

            fun setlDecks(vals: List<User_Decks>?) {
                lDecks = vals
            }

            fun setlInventory(vals: List<User_Inventory>?) {
                lInventory = vals
            }

            fun setDeckNameCards(vals: List<User_Cards>?) {
                deckNameCards = vals
            }

            fun addCard(userCard: User_Cards?) {
                if ( userCard != null ) rpgViewModel.insert(userCard)
                else Log.d("NULL_ERROR","userCard being added is null")
            }

            fun addDeck(userDeck: User_Decks?) {
                if ( userDeck != null ) rpgViewModel.insert(userDeck)
                else Log.d("NULL_ERROR","userDeck being added is null")
            }

            fun addInventory(userInventory: User_Inventory?) {
                if ( userInventory != null ) rpgViewModel.insert(userInventory)
                else Log.d("NULL_ERROR","userInventory being added is null")
            }

            fun addEQPlayed(userEQPlayed: User_EQPlayed?) {
                if ( userEQPlayed != null ) rpgViewModel.insert(userEQPlayed)
                else Log.d("NULL_ERROR","userEQPlayed being added is null")
            }

            fun deleteInventory(userInventory: User_Inventory?) {
                if ( userInventory != null ) rpgViewModel.deleteInventory(userInventory)
                else Log.d("NULL_ERROR","userInventory being removed is null")
            }

            fun deleteCard(userCard: User_Cards?) {
                if ( userCard != null ) rpgViewModel.deleteCard(userCard)
                else Log.d("NULL_ERROR","userCard being removed is null")
            }

            fun deleteDeck(userDeck: User_Decks?) {
                if ( userDeck != null ) rpgViewModel.deleteDeck(userDeck)
                else Log.d("NULL_ERROR","userDeck being removed is null")
            }

            fun deleteEQPlayed(userEqPlayed: User_EQPlayed?) {
                if ( userEqPlayed!= null ) rpgViewModel.deleteEQPlayed(userEqPlayed)
                else Log.d("NULL_ERROR","userEQPlaeyd being removed is null")
            }

            //please remember these are located by ID NUMBER so the given userData must be FROM THE DB, >>NOT<< NEW
            private fun updateRegion(userValues: User_Values) {
                rpgViewModel.updateRegion(userValues)
            }

            private fun updatePhase(userValues: User_Values) {
                rpgViewModel.updatePhase(userValues)
            }

            private fun updateFrontChar(userValues: User_Values) {
                rpgViewModel.updateFrontChar(userValues)
            }

            private fun updateOkane(userValues: User_Values) {
                rpgViewModel.updateOkane(userValues)
            }

            private fun updateCompleted(userEQPlayed: User_EQPlayed) {
                rpgViewModel.updateCompleted(userEQPlayed)
            }

            private fun updateLevel(userCharacters: User_Characters) {
                rpgViewModel.updateLevel(userCharacters)
            }

            private fun updateRank(userCharacters: User_Characters) {
                rpgViewModel.updateRank(userCharacters)
            }

            private fun updateDeck(userCharacters: User_Characters) {
                rpgViewModel.updateDeck(userCharacters)
            }

            private fun updateItem(userCharacters: User_Characters) {
                rpgViewModel.updateItem(userCharacters)
            }

            private fun updateVenelandEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion1exp(userCharacters)
            }

            private fun updatePietasEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion23exp(userCharacters)
            }

            private fun updateStonesEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion4exp(userCharacters)
            }

            private fun updateHdrEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion5exp(userCharacters)
            }

            private fun updateRegion6EXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion6exp(userCharacters)
            }

            private fun updateRegion7EXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion7exp(userCharacters)
            }

            private fun updateRegion89EXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion89exp(userCharacters)
            }

            private fun updateRegion10EXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion10exp(userCharacters)
            }

            private fun updateRegion11EXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion11exp(userCharacters)
            }

            private fun updateNebulaEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion12exp(userCharacters)
            }

            private fun updateRegion13EXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion13exp(userCharacters)
            }

            private fun updateIcecubeEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion14exp(userCharacters)
            }

            private fun updateRupesEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion16exp(userCharacters)
            }

            private fun updatePetraEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion17exp(userCharacters)
            }

            private fun updateJuslynEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion18exp(userCharacters)
            }

            private fun updateMaceriaEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion19exp(userCharacters)
            }

            private fun updateNorthEXP(userCharacters: User_Characters) {
                rpgViewModel.updateRegion20exp(userCharacters)
            }

            private fun updateAmount(userInventory: User_Inventory) {
                rpgViewModel.updateAmount(userInventory)
            }

            private fun updateChar(userDecks: User_Decks) {
                rpgViewModel.updateChar(userDecks)
            }

            private fun updateLabel(userDecks: User_Decks) {
                rpgViewModel.updateLabel(userDecks)
            }

            private fun updateLen(userDecks: User_Decks) {
                rpgViewModel.updateLen(userDecks)
            }

            fun updateAmount(userCards: User_Cards) {
                rpgViewModel.updateAmount(userCards)
            }

            private fun updatePosition(userCards: User_Cards) {
                rpgViewModel.updatePosition(userCards)
            }

            fun getNameCards(cardName: String?): List<User_Cards>? {
                rpgViewModel.findNameCards(cardName ?: "")
                return deckNameCards
            }

            fun fillDecks() {
                thisUserInventory.fillDecks(
                    lDecks as ArrayList<User_Decks>?,
                    lCards as ArrayList<User_Cards>?
                )
            }

            fun removeDeck(name: String) {
                //first remove the User_Cards in this deck
                run {
                    var n = 0
                    while (n < lCards!!.size) {
                        if (lCards!![n].deck.compareTo(name) == 0) { //the card is in the deck
                            deleteCard(lCards!![n])
                            Log.d(
                                "NOTICE",
                                "index is modified... might be removing a card"
                            )
                            //this index is decreased because lCards is assumed to get modified because the db is modified
                            n--
                        }
                        n++
                    }
                }
                //remove the actual User_Decks from the db
                var tempdeck: User_Decks? = null
                for (n in lDecks!!.indices) {
                    if (lDecks!![n].name.compareTo(name) == 0) {
                        tempdeck = lDecks!![n]
                    }
                }
                if (tempdeck == null) {
                    throw RuntimeException("Trying to remove deck: $name, but it DNE in db")
                }
                deleteDeck(tempdeck)
            }

            val currentRegion: regions?
                get() = this_pl.getRegion(lValues!![0].cur_region)

            val cur_character: Characters
                get() {
                    var character: Characters = Katherine(0)
                    if (lValues != null) {
                        var possibility: Characters? = this_pl.getCharacter(lValues!![0].front_char)
                        if (possibility != null) character = possibility
                    }
                    return character
                }

            //change this to be a real weapon object plz
            val cur_weapon: String
                get() {
                    var weapon = "default"
                    if (lCharacters != null) {
                        for (n in lCharacters!!.indices) {
                            if (lCharacters!![n].name.equals(cur_character.name)) {
                                weapon = lCharacters!![n].weapon_equip
                                break
                            }
                        }
                    }
                    return weapon
                }

            fun changeCharacter(character: Characters) {
                val value: User_Values = lValues!![0]
                value.front_char = character.name
                updateFrontChar(value)
            }

            val katieLevelExp: Int
                get() = lCharacters?.get(0)?.level ?: 0

            val deltaLevelExp: Int
                get() = lCharacters?.get(1)?.level ?: 0

            val vivLevelExp: Int
                get() = lCharacters?.get(2)?.level ?: 0

            val gammaLevelExP: Int
                get() = lCharacters?.get(3)?.level ?: 0

            fun changeSize(deck: User_Decks, size: Int) {
                deck.length = size
                updateLen(deck)
            }

            fun changeOkane(okane: Int) {
                val value: User_Values = lValues!![0]
                value.cur_okane = okane
                updateOkane(value)
            }

            fun changeAmt(card: User_Cards, amt: Int) {
                card.amount = amt
                updateAmount(card)
            }

            fun changeRegionExp(region: regions, character: main_character, newEXP: Int
            ) {
                val regionNum: Int = region.regionNum
                //create userCharacters
                val tempUserCharacter: User_Characters
                tempUserCharacter =
                    if (character.toString().compareTo(lCharacters!![0].name) == 0) { //character is Katherine
                        lCharacters!![0]
                    } else if (character.toString().compareTo(lCharacters!![1].name) == 0) { //character is Delta
                        lCharacters!![1]
                    } else if (character.toString().compareTo(lCharacters!![2].name) == 0) { //character is Vivian
                        lCharacters!![2]
                    } else {
                        throw RuntimeException("Character name " + character.toString().toString() + " doesn't match any saved character")
                    }
                if (regionNum == 1) {
                    tempUserCharacter.region1exp = newEXP
                    updateVenelandEXP(tempUserCharacter)
                } else if (regionNum == 23) {
                    tempUserCharacter.region23exp = newEXP
                    updatePietasEXP(tempUserCharacter)
                } else if (regionNum == 4) {
                    tempUserCharacter.region4exp = newEXP
                    updateStonesEXP(tempUserCharacter)
                } else if (regionNum == 5) {
                    tempUserCharacter.region5exp = newEXP
                    updateHdrEXP(tempUserCharacter)
                } else if (regionNum == 6) {
                    tempUserCharacter.region6exp = newEXP
                    updateRegion6EXP(tempUserCharacter)
                } else if (regionNum == 7) {
                    tempUserCharacter.region7exp = newEXP
                    updateRegion7EXP(tempUserCharacter)
                } else if (regionNum == 89) {
                    tempUserCharacter.region89exp = newEXP
                    updateRegion89EXP(tempUserCharacter)
                } else if (regionNum == 10) {
                    tempUserCharacter.region10exp = newEXP
                    updateRegion10EXP(tempUserCharacter)
                } else if (regionNum == 11) {
                    tempUserCharacter.region11exp = newEXP
                    updateRegion11EXP(tempUserCharacter)
                } else if (regionNum == 12) {
                    tempUserCharacter.region12exp = newEXP
                    updateNebulaEXP(tempUserCharacter)
                } else if (regionNum == 13) {
                    tempUserCharacter.region13exp = newEXP
                    updateRegion13EXP(tempUserCharacter)
                } else if (regionNum == 14) {
                    tempUserCharacter.region14exp = newEXP
                    updateIcecubeEXP(tempUserCharacter)
                } else if (regionNum == 16) {
                    tempUserCharacter.region16exp = newEXP
                    updateRupesEXP(tempUserCharacter)
                } else if (regionNum == 17) {
                    tempUserCharacter.region17exp = newEXP
                    updatePetraEXP(tempUserCharacter)
                } else if (regionNum == 18) {
                    tempUserCharacter.region18exp = newEXP
                    updateJuslynEXP(tempUserCharacter)
                } else if (regionNum == 19) {
                    tempUserCharacter.region19exp = newEXP
                    updateJuslynEXP(tempUserCharacter)
                } else if (regionNum == 20) {
                    tempUserCharacter.region20exp = newEXP
                    updateNorthEXP(tempUserCharacter)
                } else {
                    throw RuntimeException("invalid region number of region " + region.nom)
                }
            }

            fun getDeck(index: Int): User_Decks {
                return lDecks!![index]
            }

            fun getCur_region(): regions {
                return cur_region
            }

            val pL: Int
                get() = if (lValues != null) lValues!![0].cur_PL else 1

            init {
                cur_region = Veneland()
                pl = 1
            }
        }

        var userDataChecker: checkUserData

        private inner class userInventory {
            private val allCards: BlankDeck
            private val allDecks: ArrayList<Deck>
            private val allDecksNames: ArrayList<String>
            //please don't load the following unless inventory is opened...the exception is those equipped by characters
            private val allWeapons: ArrayList<Weapon>
            private val allNonWeapons: ArrayList<inventI>
            private val allItems: ArrayList<inventI>
            var cardConverter: str2card = str2card()
            fun fillDecks(lDecks: ArrayList<User_Decks>?, lCards: ArrayList<User_Cards>?)
            { //the db cards need to be in alphabetical order
                var lCards2: ArrayList<User_Cards>? = lCards
                lCards2 = alphabetizelCards(lCards2)
                if (allDecks.size == 0) {
                    for (d in lDecks!!) {
                        var charer: String?
                        charer = d.char_equip
                        if (d.char_equip.equals("None")) {
                            charer = null
                        }
                        addDeck(Deck(d.name, charer), true)
                    }
                    var previous_card = ""
                    var previous_deck = ""
                    var deckAmt = 0
                    var temp: Card? = null
                    var sudoCardIndex = 0
                    for (c in lCards2) { //if card is not the same as the previous OR null, create a new card instance
                        if (temp == null || previous_card != c.name) {
                            temp = cardConverter.getCard(c.name)
                            sudoCardIndex = allCards.addCard(temp)
                            if (!c.deck.equals("None")) {
                                Log.d("TEST",c.deck+" "+temp.nom)
                                allDecks[Collections.binarySearch(allDecksNames, c.deck)].addCard(temp)
                            }
                            previous_deck = c.deck
                            deckAmt = 1
                            previous_card = c.name
                        } else {
                            if (previous_deck == c.deck) { //same deck as the last card
                                if (deckAmt < allCards.getSudoCard(sudoCardIndex)?.size ?: 0) {
                                    //this type of card in this deck has less than the number of cards created
                                    //>>thus use a card in allCards sudoCard
                                    if (!c.deck.equals("None")) {
                                        allDecks[Collections.binarySearch(allDecksNames, c.deck)].addCard(
                                            allCards.getSudoCard(sudoCardIndex)?.getCard(deckAmt)
                                        )
                                    }
                                }
                                else {
                                    //the amount of cards in this deck are the same or greater than cards current created
                                    //please create more
                                    if (deckAmt >= c.amount) {
                                        throw RuntimeException("cards " + c.name + " created exceed the said db amount")
                                    }
                                    temp = cardConverter.getPrevious(previous_card)
                                    allCards.addCard(temp)
                                    if (!c.deck.equals("None")) {
                                        allDecks[Collections.binarySearch(allDecksNames, c.deck)].addCard(temp)
                                    }
                                }
                                deckAmt++
                            } else { //new (diff) deck from the last card
                                previous_deck = c.deck
                                deckAmt = 1
                                if (!c.deck.equals("None")) {
                                    allDecks[Collections.binarySearch(
                                        allDecksNames,
                                        c.deck
                                    )].addCard(allCards.getSudoCard(sudoCardIndex)?.getCard(0))
                                }
                                else { //if the deck is "None" you probably need to make a new card...
                                    if (c.amount > allCards.getSudoCard(sudoCardIndex)?.size ?: 0) { //we still need to make a new card
                                        temp = cardConverter.getPrevious(previous_card)
                                        allCards.addCard(temp)
                                    }
                                }
                            }
                        }
                    }
                } else {
                    throw RuntimeException("Cannot fill decks when they're not empty")
                }
            }

            fun addCardtoDeck(deck: Deck, card: Card) {
                val amt: Int =
                    alphabetizelCards(userDataChecker.getNameCards(card.toString()) as ArrayList<User_Cards>?)[0].amount
                val toAdd = User_Cards(card.toString(), amt, deck.nom, 0)
                addUserCardToDeck(toAdd)
                deck.addCard(card)
            }

            fun addCard(card: Card) {
                val alphaUserCards: ArrayList<User_Cards> =
                    alphabetizelCards(userDataChecker.getNameCards(card.toString()) as ArrayList<User_Cards>?)
                val newCard =
                    User_Cards(card.toString(), alphaUserCards[0].amount + 1, "None", 0)
                for (n in alphaUserCards.indices) {
                    alphaUserCards[n].amount = (newCard.amount)
                    userDataChecker.updateAmount(alphaUserCards[n])
                }
                userDataChecker.addCard(newCard)
                allCards.addCard(card)
            }

            fun removeCardfromDeck(deck: Deck, card: Card) {
                val alphaUserCards: ArrayList<User_Cards> =
                    alphabetizelCards(userDataChecker.getNameCards(card.toString()) as ArrayList<User_Cards>?)
                val toRemove: User_Cards = cardsOfDeck(deck.nom, alphaUserCards)!![0]
                //!!!new plan. for each card there will be a "None" card in the db...
//im assuming fillDecks() will work with this...... you'll need to do an indv test later one to make sure!!!
                removeUserCardFromDeck(toRemove)
                deck.removeCard(card)
            }

            private fun removeUserCardFromDeck(card: User_Cards) { //userDataChecker.deleteCard(card);
                val userDeck: User_Decks = userDataChecker.getDeck(
                    Collections.binarySearch(
                        allDecksNames,
                        card.deck
                    )
                )
                userDataChecker.changeSize(userDeck, userDeck.length - 1)
            }

            private fun addUserCardToDeck(card: User_Cards) {
                val userDeck: User_Decks = userDataChecker.getDeck(
                    Collections.binarySearch(
                        allDecksNames,
                        card.deck
                    )
                )
                userDataChecker.changeSize(userDeck, userDeck.length + 1)
            }

            fun removeCard(card: Card) {
                val alphaUserCards: ArrayList<User_Cards> =
                    alphabetizelCards(userDataChecker.getNameCards(card.toString()) as ArrayList<User_Cards>?)
                val toRemove: User_Cards = cardsOfDeck("None", alphaUserCards)!![0]
                userDataChecker.deleteCard(toRemove)
                alphaUserCards.remove(toRemove)
                val amount: Int = toRemove.amount - 1
                var prev_deck = ""
                for (i in alphaUserCards.indices) { //change the amt quantity in all of the cards
                    userDataChecker.changeAmt(alphaUserCards[i], amount)
                    //check that the amt in each deck doesn't exceed this new amount
                    if (prev_deck != "" && prev_deck != alphaUserCards[i].deck) { //first, check the previous cards to make sure their deck_amt !> amount
                        val temp_deck_index =
                            Collections.binarySearch(
                                allDecksNames,
                                prev_deck
                            )
                        val temp_deck: Deck = allDecks[temp_deck_index]
                        if ((temp_deck.getSudoCard(alphaUserCards[i].name)).size > amount) { //rmove card from the deck
                            removeUserCardFromDeck(toRemove)
                            temp_deck.removeLastCard(toRemove.name)
                        }
                    } else if (prev_deck == "") {
                        prev_deck = alphaUserCards[i].deck
                    }
                }
                allCards.removeCard(card)
            }

            fun cardsOfDeck(
                deckName: String,
                lCards: ArrayList<User_Cards>
            ): ArrayList<User_Cards>? {
                val cards: ArrayList<User_Cards> = ArrayList<User_Cards>()
                var location = binarySearchlCards(0, lCards.size - 1, deckName, lCards)
                if (location == -1) {
                    return null
                }
                while (location != 0 && lCards[location - 1].deck.compareTo(deckName) == 0) { //the card before this is also the same card
                    location--
                }
                while (location != lCards.size - 1 && lCards[location].toString().compareTo(
                        deckName
                    ) == 0
                ) {
                    cards.add(lCards[location])
                    location++
                }
                return cards
            }

            fun lCards2Cards(lCards: ArrayList<User_Cards>): ArrayList<Card?> {
                val cards: ArrayList<Card?> = ArrayList<Card?>()
                for (c in lCards) {
                    cards.add(cardConverter.getCard(c.name))
                }
                return cards
            }

            private fun alphabetizelCards(lCards: ArrayList<User_Cards>?): ArrayList<User_Cards> {
                val alphabetcards: ArrayList<User_Cards> =
                    ArrayList<User_Cards>()
                var j: Int
                var newIndex = 0
                alphabetcards.add(lCards!![0])
                for (i in 1 until lCards.size) {
                    alphabetcards.add(lCards[i])
                    j = i
                    var stillLarger = true
                    while (stillLarger) {
                        j--
                        if (alphabetcards[j].name.compareTo(alphabetcards[i].name) < 0) {
                            stillLarger = false
                            newIndex = j + 1
                        } else if (alphabetcards[j].name.compareTo(alphabetcards[i].name) == 0) { //the names are the same...sort by deck
                            if (alphabetcards[j].deck.compareTo(alphabetcards[i].deck) == 0) {
                                stillLarger = false
                                newIndex = j + 1
                            } else {
                                while (stillLarger) {
                                    if (alphabetcards[j].name.compareTo(alphabetcards[i].name) == 0) {
                                        if (alphabetcards[j].deck.compareTo(
                                                alphabetcards[i].deck
                                            ) <= 0
                                        ) {
                                            stillLarger = false
                                            newIndex = j + 1
                                        } else if (j == 0) {
                                            stillLarger = false
                                            newIndex = 0
                                        }
                                    } else {
                                        stillLarger = false
                                        newIndex = j + 1
                                    }
                                    j--
                                }
                            }
                        } else if (j == 0) {
                            stillLarger = false
                            newIndex = 0
                        }
                    }
                    alphabetcards.add(newIndex, alphabetcards[i])
                    alphabetcards.removeAt(i + 1)
                }
                return alphabetcards
            }

            //indb == if the deck is already in the db or not
            fun addDeck(addedDeck: Deck, indb: Boolean) { //insertion sort
                if (allDecks.size == 0) {
                    allDecks.add(addedDeck)
                    allDecksNames.add(addedDeck.nom)
                } else {
                    var stillLarger = true
                    var newIndex = 0
                    var j = allDecks.size
                    while (stillLarger) {
                        j--
                        val compare: Int = allDecks[j].instanceName.compareTo(addedDeck.instanceName)
                        if (compare < 0) {
                            stillLarger = false
                            newIndex = j + 1
                        }
                        else if (compare == 0) { //eventually you'll need to give the user a dialog box telling them to edit the name of their deck
                            throw RuntimeException("INVALID DECK NAME: deck name is the same as another deck...")
                        }
                        else if (j == 0) {
                            stillLarger = false
                            newIndex = 0
                        }
                    }
                    allDecks.add(newIndex, addedDeck)
                    allDecksNames.add(newIndex, addedDeck.nom)
                }
                if (!indb) {
                    userDataChecker.addDeck(
                        User_Decks(
                            addedDeck.nom,
                            "None",
                            addedDeck.cardAmt
                        )
                    )
                }
            }

            fun removeDeck(removeDeck: Deck) { //all this does is update the deck instances of Cards
//it doesnt remove the cards from the lists in the deck... hence why it is still used in the following:
                removeDeck.removeAll()
                //this will remove the deck card instances from the db...perhaps theres a better way to do this?
                userDataChecker.removeDeck(removeDeck.nom)
                //removing deck from relative allDecks object
                allDecks.remove(removeDeck)
                //todo check if a character has this deck equipped... you'll need to modify that too if it's true, db and relative
            }

            private fun binarySearchlCards(
                startIndex: Int,
                endIndex: Int,
                deckName: String,
                lCards: ArrayList<User_Cards>
            ): Int {
                if (endIndex >= startIndex) {
                    val mid =
                        startIndex + ((endIndex - startIndex).toDouble() / 2 + .5).toInt()
                    // If the element is present at the
// middle itself
                    if (lCards[mid].deck.compareTo(deckName) == 0) return mid
                    // If element is smaller than mid, then
// it can only be present in left subarray
                    return if (lCards[mid].deck.compareTo(deckName) > 0) binarySearchlCards(
                        startIndex,
                        mid - 1,
                        deckName,
                        lCards
                    ) else binarySearchlCards(mid + 1, endIndex, deckName, lCards)
                    // Else the element can only be present
// in right subarray
                }
                // We reach here when element is not present
// in array
                return -1
            }

            fun getAllCards(): BlankDeck {
                return allCards
            }

            fun getAllDecks(): ArrayList<Deck> {
                return allDecks
            }

            fun getAllWeapons(): ArrayList<Weapon> {
                if (allWeapons.size == 0) { //create inventory options
                }
                return allWeapons
            }

            fun getAllNonWeapons(): ArrayList<inventI> {
                if (allNonWeapons.size == 0) { //create inventory options
                }
                return allNonWeapons
            }

            fun getAllItems(): ArrayList<inventI> {
                if (allWeapons.size == 0) { //create inventory options
                }
                return allItems
            }

            fun createInventoryOptions() { //not finished
                if (allWeapons.size == 0) { //create all Weapons
                }
                if (allNonWeapons.size == 0) { //create all non weapons
                }
                if (allItems.size == 0) {
                    for (i in allWeapons.indices) {
                        allItems.add(allWeapons[i])
                    }
                    for (i in allNonWeapons.indices) {
                        allItems.add(allNonWeapons[i])
                    }
                }
            }

            init {
                allCards = BlankDeck()
                allDecks = ArrayList<Deck>()
                allWeapons = ArrayList<Weapon>()
                allNonWeapons = ArrayList<inventI>()
                allItems = ArrayList<inventI>()
                allDecksNames = ArrayList()
            }
        }

        var thisUserInventory: userInventory
        fun setlValues(vals: List<User_Values>?) {
            userDataChecker.setlValues(vals)
        }

        fun setlCharacters(lCharacters: List<User_Characters>?) {
            userDataChecker.setlCharacters(lCharacters)
        }

        fun setlEQPlayed(lEQPlayed: List<User_EQPlayed>?) {
            userDataChecker.setlEQPlayed(lEQPlayed)
        }

        fun setlCards(vals: List<User_Cards>?) {
            userDataChecker.setlCards(vals)
        }

        fun setlDecks(vals: List<User_Decks>?) {
            userDataChecker.setlDecks(vals)
        }

        fun setlInventory(vals: List<User_Inventory>?) {
            userDataChecker.setlInventory(vals)
        }

        fun setDeckNameCards(vals: List<User_Cards>?) {
            userDataChecker.setDeckNameCards(vals)
        }

        val cur_region: regions
            get() = userDataChecker.getCur_region()

        val cur_character: Characters
            get() = userDataChecker.cur_character

        val cur_weapon: String
            get() = userDataChecker.cur_weapon

        val pL: Int
            get() = userDataChecker.pL

        fun changeCharacter(character: Characters) {
            userDataChecker.changeCharacter(character)
        }

        fun fillDecks() {
            userDataChecker.fillDecks()
        }

        //an existing card is added to a deck
        fun addCardtoDeck(deck: Deck, card: Card) {
            thisUserInventory.addCardtoDeck(deck, card)
        }

        //an existing card is removed from a deck, but continues to exist
        fun removeCardfromDeck(deck: Deck, card: Card) {
            thisUserInventory.removeCardfromDeck(deck, card)
        }

        //does not add to a deck, card instance is assumed to not exist
        fun addCard(card: Card) {
            thisUserInventory.addCard(card)
        }

        //an existing card is removed from inventory entirely, including all decks
        fun removeCard(card: Card) {
            thisUserInventory.removeCard(card)
        }

        //creating a new deck...it is assumed this does not already exist in the db
        fun addDeck(deck: Deck) {
            thisUserInventory.addDeck(deck, false)
        }

        fun removeDeck(deck: Deck) {
            thisUserInventory.removeDeck(deck)
        }

        //returns a BlankDeck...not a list
        val allCards: BlankDeck
            get() = thisUserInventory.getAllCards()

        val allDecks: ArrayList<Deck>
            get() = thisUserInventory.getAllDecks()

        fun setKatieLevel(level: Int) {
            if (level == 0) {
            } else KatieLevel = level
        }

        fun setVivLevel(level: Int) {
            if (level == 0) {
                PL_VendingMachine.getInitLevel(userDataChecker.vivLevelExp)
            } else VivLevel = level
        }

        fun setDeltaLevel(level: Int) {
            if (level == 0) {
                PL_VendingMachine.getInitLevel(userDataChecker.deltaLevelExp)
            } else DeltaLevel = level
        }

        init {
            userDataChecker = checkUserData()
            thisUserInventory = userInventory()
        }
    }

    private var myDataController = dataController()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        var binding : ActivityMainmenyuBinding = DataBindingUtil.setContentView(this, R.layout.activity_mainmenyu)

        this_pl = PL_VendingMachine.getPL(1)

        val navController = this.findNavController(R.id.mainMenyuHostFragment)
        //NavigationUI.setupActionBarWithNavController(this,navController)

        rpgViewModel = ViewModelProvider(this).get(RPG_ViewModel::class.java)

        setObservers()
    }

    fun setObservers() {
        updateUserValuesCounter = 0
        updateUserCardsCounter = 0
        updateUserCharactersCounter = 0
        updateUserDecksCounter = 0
        updateUserEQPlayedCounter = 0
        updateUserInventoryCounter = 0
        rpgViewModel.getlUserValues()!!.observe(
            this,
            Observer<List<User_Values?>?> { vals ->
                updateUserValuesCounter++
                myDataController.setlValues(vals?.filterNotNull())
                if (updateUserValuesCounter == 1) {
                    regionBackground.setImageDrawable(getDrawable(myDataController.cur_region.drawable_background))
                    //filling region fragment list
                    //fillRegions_frag();
                }
            })
        rpgViewModel.getlUserEQPlayed()!!.observe(
            this,
            Observer<List<User_EQPlayed?>?> { vals ->
                updateUserEQPlayedCounter++
                myDataController.setlEQPlayed(vals?.filterNotNull())
                if (updateUserEQPlayedCounter == 1) { //init UI updates here
                }
            })
        rpgViewModel.getlUserCharacters()!!.observe(
            this,
            Observer<List<User_Characters?>?> { vals ->
                updateUserCharactersCounter++
                myDataController.setlCharacters(vals?.filterNotNull())
                if (updateUserCharactersCounter == 1) {
                    myDataController.setKatieLevel(0)
                    myDataController.setVivLevel(0)
                    myDataController.setDeltaLevel(0)
                }
            })
        rpgViewModel.getlUserCards()!!.observe(
            this,
            Observer<List<User_Cards?>?> { vals ->
                updateUserCardsCounter++
                myDataController.setlCards(vals?.filterNotNull())
                if (updateUserCardsCounter == 1) { //init UI updates here
                    if (updateUserDecksCounter == 1) {
                        myDataController.fillDecks()
                    }
                }
            })
        rpgViewModel.getlUserDecks()!!.observe(
            this,
            Observer<List<User_Decks?>?> { vals ->
                updateUserDecksCounter++
                myDataController.setlDecks(vals?.filterNotNull())
                if (updateUserDecksCounter == 1) { //init UI updates here
                    if (updateUserCardsCounter == 1) {
                        myDataController.fillDecks()
                    }
                }
            })
        rpgViewModel.getlUserInventory()!!.observe(
            this,
            Observer<List<User_Inventory?>?> { vals ->
                updateUserInventoryCounter++
                myDataController.setlInventory(vals?.filterNotNull())
                if (updateUserInventoryCounter == 1) { //init UI updates here
                }
            })
        rpgViewModel.nameCards?.observe(
            this,
            Observer<List<User_Cards?>?> { vals -> myDataController.setDeckNameCards(vals?.filterNotNull()) })
    }

    override fun upPressed() {

        val navController = this.findNavController(R.id.mainMenyuHostFragment)
        navController.navigateUp()


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.mainMenyuHostFragment)
        return navController.navigateUp()
    }

    fun updateAllPL(pl: Int) {
        //val navController = this.findNavController(R.id.mainMenyuHostFragment)
        val fm: FragmentManager = getSupportFragmentManager()
        val allFragments: List<Fragment> = fm.fragments.toList()
        for (x in allFragments) {
            if (x is updateAllPL) {
                x.lemmeupdatethatpl(pl)
            }
        }
    }

    //for mainMenyuFragment to read data
    override fun grabRegion(): Int {
        return myDataController.cur_region.drawable_background
    }
    override fun grabMMC(): String {
        return myDataController.cur_character.name
    }
    override fun grabCUR_PL(): PL {
        return this_pl
    }

    override fun SUBupdateDB_mmc(mainCharacter: main_character, parentFragment: Fragment) {
        myDataController.changeCharacter(mainCharacter)
        Log.d("DEBUG",myDataController.cur_character.name)
        (parentFragment as characterViewInterfaceIn).updateMMC(mainCharacter)
    }

}
