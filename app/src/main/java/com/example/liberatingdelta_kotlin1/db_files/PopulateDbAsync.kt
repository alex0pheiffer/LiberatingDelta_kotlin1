package com.example.liberatingdelta_kotlin1.db_files

import android.os.AsyncTask

class PopulateDbAsync internal constructor(db: RPG_RoomDatabase?) :
    AsyncTask<Void?, Void?, Void?>() {
    private val userValues: User_Values_Dao?
    private val userCharacters: User_Characters_Dao?
    private val userCards: User_Cards_Dao?
    private val userDecks: User_Decks_Dao?
    private val userEQPlayed: User_EQPlayed_Dao?
    private val userInventory: User_Inventory_Dao?
    private fun removeAll() {
        userValues!!.deleteAll()
        userCharacters!!.deleteAll()
        userCards!!.deleteAll()
        userDecks!!.deleteAll()
        userEQPlayed!!.deleteAll()
        userInventory!!.deleteAll()
    }

    protected override fun doInBackground(vararg params: Void?): Void? {
        this.removeAll()
        val useval = User_Values(1, 0, 0, "Katherine", "none", "none", "Veneland")
        userValues!!.insert(useval)
        //it's very important that the order of the characters is KATIE, DELTA, VIVIAN
        var usechar = User_Characters(
            "Katherine",
            0,
            0,
            "SimpleStaff",
            "BasicDeck",
            "PotionA",
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        )
        userCharacters!!.insert(usechar)
        usechar = User_Characters(
            "Delta",
            0,
            0,
            "SimpleSword",
            "none",
            "none",
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        )
        userCharacters.insert(usechar)
        usechar = User_Characters(
            "Vivian",
            0,
            0,
            "SimpleBow",
            "none",
            "none",
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        )
        userCharacters.insert(usechar)
        var usecard = User_Cards("RockToss", 3, "BasicDeck", 0)
        userCards!!.insert(usecard)
        usecard = User_Cards("RockToss", 3, "BasicDeck", 1)
        userCards.insert(usecard)
        usecard = User_Cards("RockToss", 3, "BasicDeck", 2)
        userCards.insert(usecard)
        usecard = User_Cards("RockToss", 3, "None", 0)
        userCards.insert(usecard)
        usecard = User_Cards("RockToss", 3, "None", 1)
        userCards.insert(usecard)
        usecard = User_Cards("RockToss", 3, "None", 2)
        userCards.insert(usecard)
        usecard = User_Cards("Shove", 3, "BasicDeck", 3)
        userCards.insert(usecard)
        usecard = User_Cards("Shove", 3, "BasicDeck", 4)
        userCards.insert(usecard)
        usecard = User_Cards("Shove", 3, "BasicDeck", 5)
        userCards.insert(usecard)
        usecard = User_Cards("Shove", 3, "None", 3)
        userCards.insert(usecard)
        usecard = User_Cards("Shove", 3, "None", 4)
        userCards.insert(usecard)
        usecard = User_Cards("Shove", 3, "None", 5)
        userCards.insert(usecard)
        usecard = User_Cards("Distract", 3, "BasicDeck", 6)
        userCards.insert(usecard)
        usecard = User_Cards("Distract", 3, "BasicDeck", 7)
        userCards.insert(usecard)
        usecard = User_Cards("Distract", 3, "BasicDeck", 8)
        userCards.insert(usecard)
        usecard = User_Cards("Distract", 3, "None", 6)
        userCards.insert(usecard)
        usecard = User_Cards("Distract", 3, "None", 7)
        userCards.insert(usecard)
        usecard = User_Cards("Distract", 3, "None", 8)
        userCards.insert(usecard)
        /*
        usecard = new User_Cards("TreeHide",3,"BasicDeck",9);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"BasicDeck",10);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"BasicDeck",11);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"None",9);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"None",10);
        userCards.insert(usecard);
        usecard = new User_Cards("TreeHide",3,"None",11);
        userCards.insert(usecard);
        */usecard = User_Cards("Scare", 3, "BasicDeck", 9)
        userCards.insert(usecard)
        usecard = User_Cards("Scare", 3, "BasicDeck", 10)
        userCards.insert(usecard)
        usecard = User_Cards("Scare", 3, "BasicDeck", 11)
        userCards.insert(usecard)
        usecard = User_Cards("Scare", 3, "None", 9)
        userCards.insert(usecard)
        usecard = User_Cards("Scare", 3, "None", 10)
        userCards.insert(usecard)
        usecard = User_Cards("Scare", 3, "None", 11)
        userCards.insert(usecard)
        usecard = User_Cards("SimpleDodge", 3, "BasicDeck", 12)
        userCards.insert(usecard)
        usecard = User_Cards("SimpleDodge", 3, "BasicDeck", 13)
        userCards.insert(usecard)
        usecard = User_Cards("SimpleDodge", 3, "BasicDeck", 14)
        userCards.insert(usecard)
        usecard = User_Cards("SimpleDodge", 3, "None", 12)
        userCards.insert(usecard)
        usecard = User_Cards("SimpleDodge", 3, "None", 13)
        userCards.insert(usecard)
        usecard = User_Cards("SimpleDodge", 3, "None", 14)
        userCards.insert(usecard)
        /*
        usecard = new User_Cards("Splash",3,"BasicDeck",15);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"BasicDeck",16);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"BasicDeck",17);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"None",15);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"None",16);
        userCards.insert(usecard);
        usecard = new User_Cards("Splash",3,"None",17);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",18);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",19);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"BasicDeck",20);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"None",18);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"None",19);
        userCards.insert(usecard);
        usecard = new User_Cards("Struggle",3,"None",20);
        userCards.insert(usecard);
        */
        val usedeck = User_Decks("BasicDeck", "Katherine", 15)
        userDecks!!.insert(usedeck)
        val useEQP = User_EQPlayed("ChallengeA", 0, 0)
        userEQPlayed!!.insert(useEQP)
        var useinv = User_Inventory("SimpleStaff", "weapon", 1)
        userInventory!!.insert(useinv)
        useinv = User_Inventory("SimpleSword", "weapon", 1)
        userInventory.insert(useinv)
        useinv = User_Inventory("SimpleBow", "weapon", 1)
        userInventory.insert(useinv)
        useinv = User_Inventory("BasicDeck", "deck", 1)
        userInventory.insert(useinv)
        useinv = User_Inventory("PotionA", "consumable", 5)
        userInventory.insert(useinv)
        return null
    }

    init {
        userValues = db!!.UserValuesDao()
        userCharacters = db.UserCharactersDao()
        userCards = db.UserCardsDao()
        userDecks = db.UserDecksDao()
        userEQPlayed = db.UserEQPlayedDao()
        userInventory = db.UserInventoryDao()
    }
}