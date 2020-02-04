package com.example.liberatingdelta_kotlin1

/*

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rpg_v4.Main_Menyu_Fragements.CharacterArrowFragment
import com.example.rpg_v4.Main_Menyu_Fragements.chapterExtended
import com.example.rpg_v4.Main_Menyu_Fragements.characterViewFragment
import com.example.rpg_v4.Main_Menyu_Fragements.deckViewFragment
import com.example.rpg_v4.Main_Menyu_Fragements.deckViewer_deckBar
import com.example.rpg_v4.Main_Menyu_Fragements.dedicatedBackBtn
import com.example.rpg_v4.Main_Menyu_Fragements.linearCardViewFragment
import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_frontcharacter
import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_regionChapters_fragment
import com.example.rpg_v4.Main_Menyu_Fragements.main_menyu_region_map_btn
import com.example.rpg_v4.Main_Menyu_Fragements.menyu_itemsbar
import com.example.rpg_v4.Main_Menyu_Fragements.region_fragments.RegionFragmentInterface
import com.example.rpg_v4.Main_Menyu_Fragements.region_fragments.region_1_fragment
import com.example.rpg_v4.basic_classes.BlankDeck
import com.example.rpg_v4.basic_classes.Card
import com.example.rpg_v4.basic_classes.Cards.*
import com.example.rpg_v4.basic_classes.Chapter
import com.example.rpg_v4.basic_classes.Characters
import com.example.rpg_v4.basic_classes.Deck
import com.example.rpg_v4.basic_classes.PL
import com.example.rpg_v4.basic_classes.Weapon
import com.example.rpg_v4.basic_classes.abstractDeck
import com.example.rpg_v4.basic_classes.cityPt
import com.example.rpg_v4.basic_classes.inventI
import com.example.rpg_v4.basic_classes.main_character
import com.example.rpg_v4.basic_classes.regions
import com.example.rpg_v4.basic_classes.str2card
import com.example.rpg_v4.basic_classes.the_MCs.Katherine
import com.example.rpg_v4.basic_classes.the_regions.Veneland
import com.example.rpg_v4.db_files.RPG_ViewModel
import com.example.rpg_v4.db_files.User_Cards
import com.example.rpg_v4.db_files.User_Characters
import com.example.rpg_v4.db_files.User_Decks
import com.example.rpg_v4.db_files.User_EQPlayed
import com.example.rpg_v4.db_files.User_Inventory
import com.example.rpg_v4.db_files.User_Values
import java.util.*

class mainMenyuOld : AppCompatActivity(),
    main_menyu_region_map_btn.onRegionMapBtnSelectedListener,
    region_1_fragment.onRegion1SelectedListener,
    main_menyu_frontcharacter.onMenyuFrontcharacterSelectedListener,
    menyu_itemsbar.onMenyuItemsBarSelectedListener,
    main_menyu_regionChapters_fragment.onRegionChaptersSelectedListener,
    com.example.rpg_v4.Main_Menyu_Fragements.chapterExtended.onChapterExtendedSelectedListener,
    deckViewFragment.deckRecyclerListener,
    CharacterArrowFragment.onCharacterArrowFragmentInteraction,
    dedicatedBackBtn.nonRegionBackButtonListener, deckViewer_deckBar.deckViewerDeckBarListener,
    characterViewFragment.characterViewFragmentListener,
    linearCardViewFragment.linearCardViewListener {
    private var pl: Int
    private var this_pl: PL? = null
    private var rpgViewModel: RPG_ViewModel? = null
    private var updateUserValuesCounter = 0
    private var updateUserEQPlayedCounter = 0
    private var updateUserCharactersCounter = 0
    private var updateUserCardsCounter = 0
    private var updateUserDecksCounter = 0
    private var updateUserInventoryCounter = 0
    //todo: check the max through the db...#rows
    private val MAX_INVENTORY = 100
    private var activity_whole: View? = null
    private var backbox_top_text: TextView? = null
    private var mainMenyuRegionMapBtn: main_menyu_region_map_btn? = null
    private var regionFragment: Fragment? = null
    private var chapterExtendedFragment: chapterExtended? = null
    private var itemsBar: menyu_itemsbar? = null
    private var characterIcon: main_menyu_frontcharacter? = null
    private var charViewer: characterViewFragment? = null
    private var deckViewerRecycler: deckViewFragment? = null
    private var deckViewerBar: deckViewer_deckBar? = null
    private var cardViewFragment: linearCardViewFragment? = null
    private val cardEditFragmentTop: linearCardViewFragment? = null
    private val cardEditFragmentBottom: linearCardViewFragment? = null
    private var backBtn: dedicatedBackBtn? = null
    private var MMCarrowUp: CharacterArrowFragment? = null
    private var MMCarrowDown: CharacterArrowFragment? = null
    private var regionChapterListRecycler: main_menyu_regionChapters_fragment? = null
    private var bufferbackgTop: View? = null
    private var bufferbackgBottom: View? = null
    private var bufferbackgLeft: View? = null
    private var bufferbackgRight: View? = null
    private var mmc_backbox: View? = null
    private var theQuickGrabDeck: abstractDeck? = null

    private inner class layoutClass internal constructor() {
        var cURRENT_LAYOUT: String? = "MAIN_MENYU_LAYOUT"
            private set
        var pREVIOUS_LAYOUT: String? = null
            private set

        fun requestChange(NEXT_LAYOUT: String?): Boolean {
            val parsedNext = pageParse(NEXT_LAYOUT)
            val parsedCurrent = pageParse(cURRENT_LAYOUT)
            return if (parsedNext == parsedCurrent || parsedNext == "MAIN_MENYU" || parsedCurrent == "MAIN_MENYU") {
                true
            } else false
        }

        fun changeLayout(NEXT_LAYOUT: String?) {
            if (requestChange(NEXT_LAYOUT)) {
                pREVIOUS_LAYOUT = cURRENT_LAYOUT
                cURRENT_LAYOUT = NEXT_LAYOUT
                Log.d("LAYOUT", "Layout Changed.")
            } else {
                throw RuntimeException("mismatch layout attempt")
            }
        }

        private fun pageParse(layout: String?): String {
            var layout = layout
            var firstUnderscore = false
            var secondUnderscore = false
            var parsed = ""
            while (!secondUnderscore) {
                if (layout!!.substring(0, 1) == "_") {
                    if (firstUnderscore) {
                        secondUnderscore = true
                    } else {
                        firstUnderscore = true
                    }
                }
                if (!secondUnderscore) {
                    parsed = parsed + layout.substring(0, 1)
                    layout = layout.substring(1)
                }
            }
            return parsed
        }

        fun getDefaultPage(layout: String?): String {
            return pageParse(layout) + "_LAYOUT"
        }

        //returns if the layout (page_page_layout) has two parts to it, making it a subpage (layout == layout_layout)
        fun isSubpage(layout: String): Boolean {
            var underscores = 0
            for (i in 0 until layout.length) {
                if (layout.substring(i, i + 1) == "_") underscores++
                if (underscores == 4) return true
            }
            //only has 3 or less underscores
            return false
        }

        fun compareParsed(layout1: String?, layout2: String?): Boolean {
            return pageParse(layout1) == pageParse(layout2)
        }

        fun compareWithCurrent(compare_layout: String): Boolean {
            return cURRENT_LAYOUT == compare_layout
        }

    }

    private val layoutSetter = layoutClass()
    private val fragmentManager = supportFragmentManager
    private val regions_frag =
        arrayOfNulls<Fragment?>(18)

    inner class dataController internal constructor() {
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
                if (pl < 3 || this_pl == null) {
                    pl = pL
                    this_pl = PL_VendingMachine.getPL(pl)
                } else if (lValues!![0].getCur_PL() !== pl) { //update our pl, it has changed
                    pl = lValues!![0].getCur_PL()
                    this_pl = PL_VendingMachine.getPL(pl)
                }
                cur_region = currentRegion
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
                rpgViewModel.insert(userCard)
            }

            fun addDeck(userDeck: User_Decks?) {
                rpgViewModel.insert(userDeck)
            }

            fun addInventory(userInventory: User_Inventory?) {
                rpgViewModel.insert(userInventory)
            }

            fun addEQPlayed(userEQPlayed: User_EQPlayed?) {
                rpgViewModel.insert(userEQPlayed)
            }

            fun deleteInventory(userInventory: User_Inventory?) {
                rpgViewModel.deleteInventory(userInventory)
            }

            fun deleteCard(userCard: User_Cards?) {
                rpgViewModel.deleteCard(userCard)
            }

            fun deleteDeck(userDeck: User_Decks?) {
                rpgViewModel.deleteDeck(userDeck)
            }

            fun deleteEQPlayed(userEqPlayed: User_EQPlayed?) {
                rpgViewModel.deleteEQPlayed(userEqPlayed)
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

            private fun updateAmount(userCards: User_Cards) {
                rpgViewModel.updateAmount(userCards)
            }

            private fun updatePosition(userCards: User_Cards) {
                rpgViewModel.updatePosition(userCards)
            }

            fun getNameCards(cardName: String?): List<User_Cards>? {
                rpgViewModel.findNameCards(cardName)
                return deckNameCards
            }

            fun fillDecks() {
                thisUserInventory.fillDecks(
                    lDecks as ArrayList<User_Decks>?,
                    lCards as ArrayList<User_Cards>?
                )
            }

            fun removeDeck(name: String) { //first remove the User_Cards in this deck
                run {
                    var n = 0
                    while (n < lCards!!.size) {
                        if (lCards!![n].getDeck().compareTo(name) === 0) { //the card is in the deck
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
                    if (lDecks!![n].getName().compareTo(name) === 0) {
                        tempdeck = lDecks!![n]
                    }
                }
                if (tempdeck == null) {
                    throw RuntimeException("Trying to remove deck: $name, but it DNE in db")
                }
                deleteDeck(tempdeck)
            }

            private val currentRegion: regions
                private get() = this_pl.getRegion(lValues!![0].getCur_region())

            private val cur_character: Characters
                private get() {
                    var character: Characters = Katherine()
                    if (lValues != null) {
                        character = this_pl.getCharacter(lValues!![0].getFront_char())
                    }
                    return character
                }

            //change this to be a real weapon object plz
            private val cur_weapon: String
                private get() {
                    var weapon = "default"
                    if (lCharacters != null) {
                        for (n in lCharacters!!.indices) {
                            if (lCharacters!![n].getName().equals(cur_character.getName())) {
                                weapon = lCharacters!![n].getWeapon_equip()
                                break
                            }
                        }
                    }
                    return weapon
                }

            fun changeCharacter(character: Characters) {
                val value: User_Values = lValues!![0]
                value.setFront_char(character.getName())
                updateFrontChar(value)
            }

            val katieLevelExp: Int
                get() = lCharacters!![0].getLevel()

            val deltaLevelExp: Int
                get() = lCharacters!![1].getLevel()

            val vivLevelExp: Int
                get() = lCharacters!![2].getLevel()

            fun changeSize(deck: User_Decks, size: Int) {
                deck.setLength(size)
                updateLen(deck)
            }

            fun changeOkane(okane: Int) {
                val value: User_Values = lValues!![0]
                value.setCur_okane(okane)
                updateOkane(value)
            }

            fun changeAmt(card: User_Cards, amt: Int) {
                card.setAmount(amt)
                updateAmount(card)
            }

            fun changeRegionExp(
                region: regions,
                character: main_character,
                newEXP: Int
            ) {
                val regionNum: Int = region.getRegionNum()
                //create userCharacters
                val tempUserCharacter: User_Characters
                tempUserCharacter =
                    if (character.toString().compareTo(lCharacters!![0].getName()) === 0) { //character is Katherine
                        lCharacters!![0]
                    } else if (character.toString().compareTo(lCharacters!![1].getName()) === 0) { //character is Delta
                        lCharacters!![1]
                    } else if (character.toString().compareTo(lCharacters!![2].getName()) === 0) { //character is Vivian
                        lCharacters!![2]
                    } else {
                        throw RuntimeException("Character name " + character.toString().toString() + " doesn't match any saved character")
                    }
                if (regionNum == 1) {
                    tempUserCharacter.setRegion1exp(newEXP)
                    updateVenelandEXP(tempUserCharacter)
                } else if (regionNum == 23) {
                    tempUserCharacter.setRegion23exp(newEXP)
                    updatePietasEXP(tempUserCharacter)
                } else if (regionNum == 4) {
                    tempUserCharacter.setRegion4exp(newEXP)
                    updateStonesEXP(tempUserCharacter)
                } else if (regionNum == 5) {
                    tempUserCharacter.setRegion5exp(newEXP)
                    updateHdrEXP(tempUserCharacter)
                } else if (regionNum == 6) {
                    tempUserCharacter.setRegion6exp(newEXP)
                    updateRegion6EXP(tempUserCharacter)
                } else if (regionNum == 7) {
                    tempUserCharacter.setRegion7exp(newEXP)
                    updateRegion7EXP(tempUserCharacter)
                } else if (regionNum == 89) {
                    tempUserCharacter.setRegion89exp(newEXP)
                    updateRegion89EXP(tempUserCharacter)
                } else if (regionNum == 10) {
                    tempUserCharacter.setRegion10exp(newEXP)
                    updateRegion10EXP(tempUserCharacter)
                } else if (regionNum == 11) {
                    tempUserCharacter.setRegion11exp(newEXP)
                    updateRegion11EXP(tempUserCharacter)
                } else if (regionNum == 12) {
                    tempUserCharacter.setRegion12exp(newEXP)
                    updateNebulaEXP(tempUserCharacter)
                } else if (regionNum == 13) {
                    tempUserCharacter.setRegion13exp(newEXP)
                    updateRegion13EXP(tempUserCharacter)
                } else if (regionNum == 14) {
                    tempUserCharacter.setRegion14exp(newEXP)
                    updateIcecubeEXP(tempUserCharacter)
                } else if (regionNum == 16) {
                    tempUserCharacter.setRegion16exp(newEXP)
                    updateRupesEXP(tempUserCharacter)
                } else if (regionNum == 17) {
                    tempUserCharacter.setRegion17exp(newEXP)
                    updatePetraEXP(tempUserCharacter)
                } else if (regionNum == 18) {
                    tempUserCharacter.setRegion18exp(newEXP)
                    updateJuslynEXP(tempUserCharacter)
                } else if (regionNum == 19) {
                    tempUserCharacter.setRegion19exp(newEXP)
                    updateJuslynEXP(tempUserCharacter)
                } else if (regionNum == 20) {
                    tempUserCharacter.setRegion20exp(newEXP)
                    updateNorthEXP(tempUserCharacter)
                } else {
                    throw RuntimeException("invalid region number of region " + region.getNom())
                }
            }

            fun getDeck(index: Int): User_Decks {
                return lDecks!![index]
            }

            fun getCur_region(): regions {
                return cur_region
            }

            val pL: Int
                get() = if (lValues != null) lValues!![0].getCur_PL() else 1

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
            fun fillDecks(
                lDecks: ArrayList<User_Decks>?,
                lCards: ArrayList<User_Cards>?
            ) { //the db cards need to be in alphabetical order
                var lCards: ArrayList<User_Cards>? = lCards
                lCards = alphabetizelCards(lCards)
                if (allDecks.size == 0) {
                    for (d in lDecks!!) {
                        var charer: String?
                        charer = d.getChar_equip()
                        if (d.getChar_equip().equals("None")) {
                            charer = null
                        }
                        addDeck(Deck(d.getName(), charer), true)
                    }
                    var previous_card = ""
                    var previous_deck = ""
                    var deckAmt = 0
                    var temp: Card? = null
                    var sudoCardIndex = 0
                    for (c in lCards) { //if card is not the same as the previous OR null, create a new card instance
                        if (temp == null || previous_card != c.getName()) {
                            temp = cardConverter.getCard(c.getName())
                            sudoCardIndex = allCards.addCard(temp)
                            if (!c.getDeck().equals("None")) {
                                allDecks[Collections.binarySearch(
                                    allDecksNames,
                                    c.getDeck()
                                )].addCard(temp)
                            }
                            previous_deck = c.getDeck()
                            deckAmt = 1
                            previous_card = c.getName()
                        } else {
                            if (previous_deck == c.getDeck()) { //same deck as the last card
                                if (deckAmt < allCards.getSudoCard(sudoCardIndex).getAmount()) { //this type of card in this deck has less than the number of cards created
//>>thus use a card in allCards sudoCard
                                    if (!c.getDeck().equals("None")) {
                                        allDecks[Collections.binarySearch(
                                            allDecksNames,
                                            c.getDeck()
                                        )].addCard(
                                            allCards.getSudoCard(sudoCardIndex).getCard(deckAmt)
                                        )
                                    }
                                } else { //the amount of cards in this deck are the same or greater than cards current created
//please create more
                                    if (deckAmt >= c.getAmount()) {
                                        throw RuntimeException("cards " + c.getName().toString() + " created exceed the said db amount")
                                    }
                                    temp = cardConverter.getPrevious(previous_card)
                                    allCards.addCard(temp)
                                    if (!c.getDeck().equals("None")) {
                                        allDecks[Collections.binarySearch(
                                            allDecksNames,
                                            c.getDeck()
                                        )].addCard(temp)
                                    }
                                }
                                deckAmt++
                            } else { //new (diff) deck from the last card
                                previous_deck = c.getDeck()
                                deckAmt = 1
                                if (!c.getDeck().equals("None")) {
                                    allDecks[Collections.binarySearch(
                                        allDecksNames,
                                        c.getDeck()
                                    )].addCard(allCards.getSudoCard(sudoCardIndex).getCard(0))
                                } else { //if the deck is "None" you probably need to make a new card...
                                    if (c.getAmount() > allCards.getSudoCard(sudoCardIndex).getAmount()) { //we still need to make a new card
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
                    alphabetizelCards(userDataChecker.getNameCards(card.toString()) as ArrayList<User_Cards>?)[0].getAmount()
                val toAdd = User_Cards(card.toString(), amt, deck.getNom(), 0)
                addUserCardToDeck(toAdd)
                deck.addCard(card)
            }

            fun addCard(card: Card) {
                val alphaUserCards: ArrayList<User_Cards> =
                    alphabetizelCards(userDataChecker.getNameCards(card.toString()) as ArrayList<User_Cards>?)
                val newCard =
                    User_Cards(card.toString(), alphaUserCards[0].getAmount() + 1, "None", 0)
                for (n in alphaUserCards.indices) {
                    alphaUserCards[n].setAmount(newCard.getAmount())
                    userDataChecker.updateAmount(alphaUserCards[n])
                }
                userDataChecker.addCard(newCard)
                allCards.addCard(card)
            }

            fun removeCardfromDeck(deck: Deck, card: Card) {
                val alphaUserCards: ArrayList<User_Cards> =
                    alphabetizelCards(userDataChecker.getNameCards(card.toString()) as ArrayList<User_Cards>?)
                val toRemove: User_Cards = cardsOfDeck(deck.getNom(), alphaUserCards)!![0]
                //!!!new plan. for each card there will be a "None" card in the db...
//im assuming fillDecks() will work with this...... you'll need to do an indv test later one to make sure!!!
                removeUserCardFromDeck(toRemove)
                deck.removeCard(card)
            }

            private fun removeUserCardFromDeck(card: User_Cards) { //userDataChecker.deleteCard(card);
                val userDeck: User_Decks = userDataChecker.getDeck(
                    Collections.binarySearch(
                        allDecksNames,
                        card.getDeck()
                    )
                )
                userDataChecker.changeSize(userDeck, userDeck.getLength() - 1)
            }

            private fun addUserCardToDeck(card: User_Cards) {
                val userDeck: User_Decks = userDataChecker.getDeck(
                    Collections.binarySearch(
                        allDecksNames,
                        card.getDeck()
                    )
                )
                userDataChecker.changeSize(userDeck, userDeck.getLength() + 1)
            }

            fun removeCard(card: Card) {
                val alphaUserCards: ArrayList<User_Cards> =
                    alphabetizelCards(userDataChecker.getNameCards(card.toString()) as ArrayList<User_Cards>?)
                val toRemove: User_Cards = cardsOfDeck("None", alphaUserCards)!![0]
                userDataChecker.deleteCard(toRemove)
                alphaUserCards.remove(toRemove)
                val amount: Int = toRemove.getAmount() - 1
                var prev_deck = ""
                for (i in alphaUserCards.indices) { //change the amt quantity in all of the cards
                    userDataChecker.changeAmt(alphaUserCards[i], amount)
                    //check that the amt in each deck doesn't exceed this new amount
                    if (prev_deck != "" && prev_deck != alphaUserCards[i].getDeck()) { //first, check the previous cards to make sure their deck_amt !> amount
                        val temp_deck_index =
                            Collections.binarySearch(
                                allDecksNames,
                                prev_deck
                            )
                        val temp_deck: Deck = allDecks[temp_deck_index]
                        if (temp_deck.getSudoCard(alphaUserCards[i].getName()).getAmount() > amount) { //rmove card from the deck
                            removeUserCardFromDeck(toRemove)
                            temp_deck.removeLastCard(toRemove.getName())
                        }
                    } else if (prev_deck == "") {
                        prev_deck = alphaUserCards[i].getDeck()
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
                while (location != 0 && lCards[location - 1].getDeck().compareTo(deckName) === 0) { //the card before this is also the same card
                    location--
                }
                while (location != lCards.size - 1 && lCards[location].toString().compareTo(
                        deckName
                    ) === 0
                ) {
                    cards.add(lCards[location])
                    location++
                }
                return cards
            }

            fun lCards2Cards(lCards: ArrayList<User_Cards>): ArrayList<Card?> {
                val tempClass: Class<*>? = null
                var temp: Card? = null
                val cards: ArrayList<Card?> = ArrayList<Card?>()
                for (c in lCards) {
                    temp = cardConverter.getCard(c.getName())
                    cards.add(temp)
                }
                return cards
            }

            private fun alphabetizelCards(lCards: ArrayList<User_Cards>?): ArrayList<User_Cards> {
                var stillLarger = true
                val alphabetcards: ArrayList<User_Cards> =
                    ArrayList<User_Cards>()
                var j: Int
                var newIndex = 0
                alphabetcards.add(lCards!![0])
                for (i in 1 until lCards.size) {
                    alphabetcards.add(lCards[i])
                    j = i
                    stillLarger = true
                    while (stillLarger) {
                        j--
                        if (alphabetcards[j].getName().compareTo(alphabetcards[i].getName()) < 0) {
                            stillLarger = false
                            newIndex = j + 1
                        } else if (alphabetcards[j].getName().compareTo(alphabetcards[i].getName()) === 0) { //the names are the same...sort by deck
                            if (alphabetcards[j].getDeck().compareTo(alphabetcards[i].getDeck()) === 0) {
                                stillLarger = false
                                newIndex = j + 1
                            } else {
                                while (stillLarger) {
                                    if (alphabetcards[j].getName().compareTo(alphabetcards[i].getName()) === 0) {
                                        if (alphabetcards[j].getDeck().compareTo(
                                                alphabetcards[i].getDeck()
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
                    allDecksNames.add(addedDeck.getNom())
                } else {
                    var stillLarger = true
                    var newIndex = 0
                    var j = allDecks.size
                    stillLarger = true
                    while (stillLarger) {
                        j--
                        val compare: Int =
                            allDecks[j].getInstanceName().compareTo(addedDeck.getInstanceName())
                        if (compare < 0) {
                            stillLarger = false
                            newIndex = j + 1
                        } else if (compare == 0) { //eventually you'll need to give the user a dialog box telling them to edit the name of their deck
                            throw RuntimeException("INVALID DECK NAME: deck name is the same as another deck...")
                        } else if (j == 0) {
                            stillLarger = false
                            newIndex = 0
                        }
                    }
                    allDecks.add(newIndex, addedDeck)
                    allDecksNames.add(newIndex, addedDeck.getNom())
                }
                if (!indb) {
                    userDataChecker.addDeck(
                        User_Decks(
                            addedDeck.getNom(),
                            "None",
                            addedDeck.getCardAmt()
                        )
                    )
                }
            }

            fun removeDeck(removeDeck: Deck) { //all this does is update the deck instances of Cards
//it doesnt remove the cards from the lists in the deck... hence why it is still used in the following:
                removeDeck.removeAll()
                //this will remove the deck card instances from the db...perhaps theres a better way to do this?
                userDataChecker.removeDeck(removeDeck.getNom())
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
                    if (lCards[mid].getDeck().compareTo(deckName) === 0) return mid
                    // If element is smaller than mid, then
// it can only be present in left subarray
                    return if (lCards[mid].getDeck().compareTo(deckName) > 0) binarySearchlCards(
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
            get() = userDataChecker.getCur_character()

        val cur_weapon: String
            get() = userDataChecker.getCur_weapon()

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

        val allDecks: ArrayList<Any>
            get() = thisUserInventory.getAllDecks()

        fun setKatieLevel(level: Int) {
            if (level == 0) {
                PL_VendingMachine.getInitLevel(userDataChecker.katieLevelExp)
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

    var myDataController: dataController? = null
    //public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenyu)
        activity_whole = findViewById(R.id.main_menyu_background)
        backbox_top_text = findViewById(R.id.mmc_backbox_top)
        bufferbackgTop = findViewById(R.id.false_background_top)
        bufferbackgBottom = findViewById(R.id.false_background_bottom)
        bufferbackgLeft = findViewById(R.id.false_background_left)
        bufferbackgRight = findViewById(R.id.false_background_right)
        mmc_backbox = findViewById(R.id.menyu_mmc_backbox)
        myDataController = dataController()
        rpgViewModel = ViewModelProvider(this).get(RPG_ViewModel::class.java)
        setObservers()
        //adding fragments
        val ft = fragmentManager.beginTransaction()
        mainMenyuRegionMapBtn =
            main_menyu_region_map_btn.newInstance(myDataController!!.cur_region.getNom(), pl)
        itemsBar = menyu_itemsbar.newInstance()
        Log.d(
            "DEBUG",
            R.drawable.katie1.toString() + " " + (myDataController!!.cur_character as main_character).getCharacterImgDrawable()
        )
        characterIcon = main_menyu_frontcharacter.newInstance(
            myDataController!!.cur_character.getName(),
            myDataController!!.cur_weapon,
            myDataController!!.pL
        )
        ft.add(R.id.menyu_regionmap_btn_frag, mainMenyuRegionMapBtn)
        ft.add(R.id.itemsbar, itemsBar)
        ft.add(R.id.menyu_mmc_frag, characterIcon)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun setObservers() {
        updateUserValuesCounter = 0
        updateUserCardsCounter = 0
        updateUserCharactersCounter = 0
        updateUserDecksCounter = 0
        updateUserEQPlayedCounter = 0
        updateUserInventoryCounter = 0
        rpgViewModel.getlUserValues().observe(
            this,
            object : Observer<List<User_Values>?> {
                override fun onChanged(vals: List<User_Values>?) {
                    updateUserValuesCounter++
                    myDataController!!.setlValues(vals)
                    if (updateUserValuesCounter == 1) {
                        activity_whole!!.background =
                            getDrawable(myDataController!!.cur_region.getDrawable_background())
                        //filling region fragment list
//fillRegions_frag();
                    }
                }
            })
        rpgViewModel.getlUserEQPlayed().observe(
            this,
            object : Observer<List<User_EQPlayed>?> {
                override fun onChanged(vals: List<User_EQPlayed>?) {
                    updateUserEQPlayedCounter++
                    myDataController!!.setlEQPlayed(vals)
                    if (updateUserEQPlayedCounter == 1) { //init UI updates here
                    }
                }
            })
        rpgViewModel.getlUserCharacters().observe(
            this,
            object : Observer<List<User_Characters>?> {
                override fun onChanged(vals: List<User_Characters>?) {
                    updateUserCharactersCounter++
                    myDataController!!.setlCharacters(vals)
                    if (updateUserCharactersCounter == 1) {
                        myDataController!!.setKatieLevel(0)
                        myDataController!!.setVivLevel(0)
                        myDataController!!.setDeltaLevel(0)
                    }
                }
            })
        rpgViewModel.getlUserCards().observe(
            this,
            object : Observer<List<User_Cards>?> {
                override fun onChanged(vals: List<User_Cards>?) {
                    updateUserCardsCounter++
                    myDataController!!.setlCards(vals)
                    if (updateUserCardsCounter == 1) { //init UI updates here
                        if (updateUserDecksCounter == 1) {
                            myDataController!!.fillDecks()
                        }
                    }
                }
            })
        rpgViewModel.getlUserDecks().observe(
            this,
            object : Observer<List<User_Decks>?> {
                override fun onChanged(vals: List<User_Decks>?) {
                    updateUserDecksCounter++
                    myDataController!!.setlDecks(vals)
                    if (updateUserDecksCounter == 1) { //init UI updates here
                        if (updateUserCardsCounter == 1) {
                            myDataController!!.fillDecks()
                        }
                    }
                }
            })
        rpgViewModel.getlUserInventory().observe(
            this,
            object : Observer<List<User_Inventory>?> {
                override fun onChanged(vals: List<User_Inventory>?) {
                    updateUserInventoryCounter++
                    myDataController!!.setlInventory(vals)
                    if (updateUserInventoryCounter == 1) { //init UI updates here
                    }
                }
            })
        rpgViewModel.getNameCards().observe(
            this,
            object : Observer<List<User_Cards>?> {
                override fun onChanged(vals: List<User_Cards>?) {
                    myDataController!!.setDeckNameCards(vals)
                }
            })
    }

    //todo
    fun fillRegions_frag() {
        println("OUR CUR PL: " + myDataController!!.pL)
        regions_frag[0] = region_1_fragment.newInstance(myDataController!!.pL)
        //regions_frag[1] = region_2_fragment.newInstance(userDataChecker.getPL());
    }

    fun regionBtnPressed() {
        if (layoutSetter.compareWithCurrent(main_menyu_region_map_btn.getCURRENT_LAYOUT())) {
            val regions_match: Boolean = myDataController!!.cur_region.getNom()
                .equals(mainMenyuRegionMapBtn.getRegion().getNom())
            if (regions_match) { //terminate the fragment
                println("Terminating RegionBtn")
                val ft =
                    fragmentManager.beginTransaction()
                regionFragment = region_1_fragment.newInstance(myDataController!!.pL)
                //this assumes that all region fragments will have the same CURRENT_LAYOUT
//BEWARE
                backBtn = dedicatedBackBtn.newInstance(pl, region_1_fragment.getCURRENT_LAYOUT())
                ft.add(R.id.dedicated_back_btn, backBtn)
                ft.add(R.id.menyu_regionmap_btn_frag, regionFragment!!)
                ft.remove(characterIcon)
                ft.remove(itemsBar)
                ft.remove(mainMenyuRegionMapBtn)
                ft.addToBackStack(null)
                ft.commit()
                bufferbackgBottom!!.alpha = 0f
                bufferbackgTop!!.alpha = 0f
                bufferbackgLeft!!.alpha = 0f
                bufferbackgRight!!.alpha = 0f
                mmc_backbox!!.background = null
                //previous was: (((RegionFragmentInterface)regionFragment).getCURRENT_LAYOUT());
                layoutSetter.changeLayout(region_1_fragment.getCURRENT_LAYOUT()) //menyu_items_bar, main_menyu_region_map_btn, main_menyu_frontcharacter
                //intro mainmenyu2region (move region icons on)
            } else {
                throw RuntimeException("mismatch regions")
            }
        } else {
            throw RuntimeException("mismatch CURRENT_LAYOUT")
        }
    }

    /*
    //regionBtns
    public void cityPtPressed(String city) {
        boolean regions_match = myDataController.getCur_region().getNom().equals(((RegionFragmentInterface)regionFragment).getRegion().getNom());
        if (regions_match) {
            //set up the cityPressed layout -- img, info, storeBtn, CH btn
            cityPt town;

            if (city.equals("Maleficere Mansion")) {
                town = this_pl.getCityPt("Maleficere Mansion",myDataController.getCur_region());
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.replace(R.id.mmc_backbox_bottom,regionChapterListRecycler);
                backbox_top_text.setText(town.getNom());
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout();
                backBtn.modifyLayout(layoutSetter.getCURRENT_LAYOUT());
            }

            else if (city.equals("Chipper Towne")) {
                town = this_pl.getCityPt("Chipper Towne",myDataController.getCur_region());
                FragmentTransaction ft = fragmentManager.beginTransaction();
                regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(myDataController.getCur_region().getNom(), town.getNom(),myDataController.getPL());
                ft.replace(R.id.mmc_backbox_bottom,regionChapterListRecycler);
                backbox_top_text.setText(town.getNom());
                ft.addToBackStack(null);
                ft.commit();
                layoutSetter.changeLayout("REGION_MAP_CITY");
                backBtn.modifyLayout(layoutSetter.getCURRENT_LAYOUT());
            }
        }
        else {
            throw new RuntimeException("mismatch regions");
        }
    }
    */
//todo change to chBtnPressed
    fun cityPtPressed(city: String) {
        val regions_match: Boolean = myDataController!!.cur_region.getNom()
            .equals((regionFragment as RegionFragmentInterface?).getRegion().getNom())
        if (regions_match) {
            val town: cityPt
            if (city == "Maleficere Mansion") {
                town = this_pl.getCityPt("Maleficere Mansion", myDataController!!.cur_region)
                val ft =
                    fragmentManager.beginTransaction()
                regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(
                    myDataController!!.cur_region.getNom(),
                    town.getNom(),
                    myDataController!!.pL
                )
                ft.replace(R.id.mmc_backbox_bottom, regionChapterListRecycler)
                backbox_top_text.setText(town.getNom())
                ft.addToBackStack(null)
                ft.commit()
                layoutSetter.changeLayout(main_menyu_regionChapters_fragment.getCURRENT_LAYOUT())
                backBtn.modifyLayout(layoutSetter.cURRENT_LAYOUT)
            } else if (city == "Chipper Towne") {
                town = this_pl.getCityPt("Chipper Towne", myDataController!!.cur_region)
                val ft =
                    fragmentManager.beginTransaction()
                regionChapterListRecycler = main_menyu_regionChapters_fragment.newInstance(
                    myDataController!!.cur_region.getNom(),
                    town.getNom(),
                    myDataController!!.pL
                )
                ft.replace(R.id.mmc_backbox_bottom, regionChapterListRecycler)
                backbox_top_text.setText(town.getNom())
                ft.addToBackStack(null)
                ft.commit()
                layoutSetter.changeLayout(main_menyu_regionChapters_fragment.getCURRENT_LAYOUT())
                backBtn.modifyLayout(layoutSetter.cURRENT_LAYOUT)
            }
        } else {
            throw RuntimeException("mismatch regions")
        }
    }

    //regionChapterListFragment
    fun chapterSelected(
        region: regions?,
        chapter: Chapter
    ) { //change the backbox_top to display the chapter name+img
//change backbox_bottom from recycleviewer to a display of
//show go button...and determine if go is available or not
        val ft = fragmentManager.beginTransaction()
        chapterExtendedFragment = chapterExtended.newInstance(pl)
        ft.replace(R.id.mmc_backbox_bottom, chapterExtendedFragment)
        backbox_top_text.setText(chapter.getNom())
        ft.addToBackStack(null)
        ft.commit()
        layoutSetter.changeLayout("REGION_MAP_CHAPTER")
        backBtn.modifyLayout(layoutSetter.cURRENT_LAYOUT)
    }

    //regionChapterExtendedFragment
    fun onChapterExtendedPressed(chapter: Chapter?) { //lol this probably isnt needed? i dont think this is going to do anything... maybe open an entire window?
    }

    //todo this is dumb. why don't i just use the MM current layout? why do i have bckbtn storing its own?
    fun backBtnPressed(layout: String) { //backbtn will return you to MAIN_MENYU_LAYOUT orrrr if the layout has an underscore, it will return you thatpage_layout
//check if the layout is a subpage
//check that the "real" current layout matches bckbtn's current layout
        if (layout.compareTo(layoutSetter.cURRENT_LAYOUT!!) != 0) {
            throw RuntimeException("Back Button's layout " + layout + " does NOT match MM layout " + layoutSetter.cURRENT_LAYOUT)
        }
        val isSubpage = layoutSetter.isSubpage(layout)
        if (!isSubpage) { //not a subpage, go to mainmenyu
            println("mm backpressed")
            clearFragments(layout)
            addFragments(layout)
            bufferbackgBottom!!.alpha = 1f
            bufferbackgTop!!.alpha = 1f
            bufferbackgLeft!!.alpha = 1f
            bufferbackgRight!!.alpha = 1f
            mmc_backbox!!.setBackgroundColor(getColor(R.color.genericElectric))
            layoutSetter.changeLayout("MAIN_MENYU_LAYOUT")
        } else { //is a subpage, go to proper layout.
            Log.d(
                "ERROR",
                "MUST INPUT THE INSTRUCTIONS FOR RETURNING TO THE PROPER SCREEN"
            )
            val defaultPage = layoutSetter.getDefaultPage(layout)
            //characterView
            if (defaultPage == characterViewFragment.getCURRENT_LAYOUT()) {
            } else if (defaultPage == deckViewFragment.getCURRENT_LAYOUT()) {
            }
            /*
            //itemView
            else if (defaultPage.equals(itemViewFragment.getCURRENT_LAYOUT())) {

            }
            */
//set the layout to the previous layout
            layoutSetter.changeLayout(layoutSetter.pREVIOUS_LAYOUT)
        }
    }

    fun goBtnPressed() {}
    //ItemsBarBtns
    fun menyuItemsBarSettingsPressed() { //new activity
    }

    fun menyuItemsBarCharactersPressed() {
        Log.d("VIEW_CHANGE", "Opening Character Viewer")
        val ft = fragmentManager.beginTransaction()
        charViewer = characterViewFragment.newInstance(pl, "Katherine", 1, 0)
        //charViewerBar = ;
        backBtn = dedicatedBackBtn.newInstance(pl, characterViewFragment.getCURRENT_LAYOUT())
        ft.add(R.id.dedicated_back_btn, backBtn)
        ft.add(R.id.whole_container_frag, charViewer)
        //ft.replace(R.id.itemsbar,deckViewerBar);
        ft.remove(mainMenyuRegionMapBtn)
        ft.addToBackStack(null)
        ft.commit()
        deployArrowsMMC(false)
        bufferbackgBottom!!.alpha = 0f
        bufferbackgTop!!.alpha = 0f
        bufferbackgLeft!!.alpha = 0f
        bufferbackgRight!!.alpha = 0f
        mmc_backbox!!.background = null
        layoutSetter.changeLayout(characterViewFragment.getCURRENT_LAYOUT()) //menyu_items_bar, main_menyu_region_map_btn, main_menyu_frontcharacter
    }

    fun menyuItemsBarPlotPressed() { //new activity
    }

    fun menyuItemsBarDecksPressed() { //bring in the charswitch arrows
//remove the region btn
//replace the itemsbar with a titlebar flush right (half the screen..)
//add a backbtn
//add deck scroller
//make sure that the middle deck in the deck scroller is the equipped deck. In the case of all char, it can be the last viewed deck... but you'll never open to all characters
        Log.d("VIEW_CHANGE", "Opening Deck Viewer")
        val ft = fragmentManager.beginTransaction()
        deckViewerRecycler = deckViewFragment.newInstance(
            myDataController!!.pL,
            myDataController!!.allDecks[0].getNom()
        )
        deckViewerBar = deckViewer_deckBar.newInstance(pl, "falseString")
        backBtn = dedicatedBackBtn.newInstance(pl, deckViewFragment.getCURRENT_LAYOUT())
        ft.add(R.id.dedicated_back_btn, backBtn)
        ft.add(R.id.whole_container_frag, deckViewerRecycler)
        ft.replace(R.id.itemsbar, deckViewerBar)
        ft.remove(mainMenyuRegionMapBtn)
        ft.addToBackStack(null)
        ft.commit()
        deployArrowsMMC(true)
        bufferbackgBottom!!.alpha = 0f
        bufferbackgTop!!.alpha = 0f
        bufferbackgLeft!!.alpha = 0f
        bufferbackgRight!!.alpha = 0f
        mmc_backbox!!.background = null
        layoutSetter.changeLayout(deckViewFragment.getCURRENT_LAYOUT()) //menyu_items_bar, main_menyu_region_map_btn, main_menyu_frontcharacter
        //intro mainmenyu2region (move region icons on)
    }

    fun menyuItemsBarInventoryPressed() {}
    fun menyuItemsBarMapPressed() { //okay i think we're opening a new activity... lets see how much this fucks up our data
        val intented = Intent(this@mainMenyuOld, wholeMapActivity::class.java)
        val args = Bundle()
        args.putString("CURRENT_REGION", myDataController!!.cur_region.getNom())
        args.putInt("PlayerLevel", pl)
        intented.putExtras(args)
        startActivity(intented)
    }

    //CharacterViewer
    fun characterViewBarStatsPressed(layout: String?) {}

    fun characterViewBarEquipPressed(layout: String?) {}
    fun characterViewBarRegionPressed(layout: String?) {}
    fun characterViewBarRankPressed(layout: String?) {}
    fun characterViewBarInfoPressed(layout: String?) {}
    //DecksViewer
    val allDecks: ArrayList<Any>
        get() = myDataController!!.allDecks

    fun deckRecyclerDeckPressed(deck: Deck) {
        Log.d("TESTING", deck.getNom().toString() + " was pressed.")
        val ft = fragmentManager.beginTransaction()
        if (deckViewerBar != null) {
            Log.d("DEBUG", "deckerView is not null")
            ft.remove(deckViewerBar)
        }
        deckViewerBar = deckViewer_deckBar.newInstance(pl, deck.getNom())
        deckViewerBar.importDeck(deck)
        ft.add(R.id.itemsbar, deckViewerBar)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun deckRecyclerAddDeckPressed() {
        Log.d("TESTING", "Adding_deck was pressed.")
        val ft = fragmentManager.beginTransaction()
        if (deckViewerBar != null) {
            ft.remove(deckViewerBar)
        }
        //todo change this to be ADDDECKFRAGTHING.getCURRENT_LAYOUT();
        layoutSetter.changeLayout("DECK_VIEW_ADD_DECK")
        //prompt new deck's name
//if input is blank, assume they dont want to make a new deck
    }

    val mMC_rightDist: Int
        get() = characterIcon.getView().getRight()

    val emptyCharacter: Boolean
        get() = characterIcon.getEmptyCharacter()

    fun decksScrolled() {
        val ft = fragmentManager.beginTransaction()
        if (deckViewerBar != null) {
            ft.remove(deckViewerBar)
        }
        ft.addToBackStack(null)
        ft.commit()
    }

    //DeckViewerBar
    fun deckViewerBar_ViewDeckPressed(deck: Deck) {
        val ft = fragmentManager.beginTransaction()
        if (deckViewerBar != null) {
            ft.remove(deckViewerBar)
        }
        reignArrowsMMC()
        ft.remove(characterIcon)
        ft.remove(deckViewerRecycler)
        cardViewFragment = linearCardViewFragment.newInstance(pl, false, deck.getNom())
        theQuickGrabDeck = deck
        //cardViewFragment.importDeck(deck);
        ft.add(R.id.cardViewRecycler_container_frag, cardViewFragment)
        //create the cardViewButton fragment here
//ft.add(R.id.cardViewButton_container_frag,cardViewbuttons);
        ft.addToBackStack(null)
        ft.commit()
    }

    fun quickGrabDeck(): abstractDeck? {
        return theQuickGrabDeck
    }

    fun deckViewerBar_showValidDeckPressed(deck: Deck?) {}
    fun deckViewerBar_DeleteDeckPressed(deck: Deck?) { //send a confirmation box...pause the lower screens?
    }

    //DeckViewCardView
    fun onListFragmentInteraction(card: sudoCard?) {}

    fun cardViewLargeBtnEditPressed(deck: abstractDeck?) {}
    //MMC_Character
    fun deployArrowsMMC(hasEmpty: Boolean) { //add the character change arrows
        MMCarrowUp = CharacterArrowFragment.newInstance(true, hasEmpty)
        MMCarrowDown = CharacterArrowFragment.newInstance(false, hasEmpty)
        characterIcon.deployArrowsMMC(MMCarrowUp, MMCarrowDown)
    }

    fun reignArrowsMMC() { //character = null
        if (characterIcon.getEmptyCharacter()) {
            Log.d("DECKTESTING", "character was null, changing...")
            characterIcon.setPreviousCharacter()
        }
        val newCharacter: Characters = characterIcon.reignArrowsMMC(MMCarrowUp, MMCarrowDown)
        myDataController!!.changeCharacter(newCharacter)
    }

    fun characterArrowPressed(isUp: Boolean, hasEmpty: Boolean) {
        characterIcon.characterArrowPressed(isUp, hasEmpty)
    }

    private fun clearFragments(layout: String) {
        if (layoutSetter.compareParsed(
                layout,
                "REGION_MAP_LAYOUT"
            )
        ) { //everything gets removed but the background
            val ft = fragmentManager.beginTransaction()
            for (fragment in fragmentManager.fragments) {
                if (fragment != null) {
                    Log.d("TRANSACTIONTEST", "removing: $fragment")
                    ft.remove(fragment)
                }
            }
            ft.addToBackStack(null)
            ft.commit()
            backbox_top_text!!.text = ""
        } else if (layoutSetter.compareParsed(layout, "CHARACTER_VIEW_LAYOUT")) {
            reignArrowsMMC()
            val ft = fragmentManager.beginTransaction()
            ft.remove(charViewer)
            ft.remove(backBtn)
            ft.addToBackStack(null)
            ft.commit()
        } else if (layoutSetter.compareParsed(layout, "DECK_VIEW_LAYOUT")) {
            reignArrowsMMC()
            val ft = fragmentManager.beginTransaction()
            ft.remove(deckViewerRecycler)
            ft.remove(backBtn)
            ft.addToBackStack(null)
            ft.commit()
        }
        println("layouts cleared")
    }

    //adding fragments to the main menyu
    private fun addFragments(layout: String) {
        if (layoutSetter.compareParsed(layout, "REGION_MAP_LAYOUT")) {
            val ft = fragmentManager.beginTransaction()
            ft.add(R.id.menyu_mmc_frag, characterIcon)
            ft.add(R.id.itemsbar, itemsBar)
            ft.add(R.id.menyu_regionmap_btn_frag, mainMenyuRegionMapBtn)
            ft.addToBackStack(null)
            ft.commit()
        } else if (layoutSetter.compareParsed(layout, "CHARACTER_VIEW_LAYOUT")) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.itemsbar, itemsBar)
            ft.add(R.id.menyu_regionmap_btn_frag, mainMenyuRegionMapBtn)
            ft.addToBackStack(null)
            ft.commit()
        } else if (layoutSetter.compareParsed(layout, "DECK_VIEW_LAYOUT")) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.itemsbar, itemsBar)
            ft.add(R.id.menyu_regionmap_btn_frag, mainMenyuRegionMapBtn)
            ft.addToBackStack(null)
            ft.commit()
        }
        println("main_menyu_layout added")
    }

    //key activity things
//
    override fun onBackPressed() { //do nothing
    }
}


*/
