package com.example.liberatingdelta_kotlin1.db_files

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RPG_RoomRepository(application: Application) {
    private val userValuesDao: User_Values_Dao
    private val userCharactersDao: User_Characters_Dao
    private val userInventoryDao: User_Inventory_Dao
    private val userCardsDao: User_Cards_Dao
    private val userDecksDao: User_Decks_Dao
    private val userEQPlayedDao: User_EQPlayed_Dao
    private val lUserValues: LiveData<List<User_Values>?>?
    private val lUserCharacters: LiveData<List<User_Characters>?>?
    private val lUserInventory: LiveData<List<User_Inventory>?>?
    private val lUserCards: LiveData<List<User_Cards>?>?
    private val lUserDecks: LiveData<List<User_Decks>?>?
    private val lUserEQPlayed: LiveData<List<User_EQPlayed>?>?
    val cardNameSearchResults = MutableLiveData<List<User_Cards>?>()

    //getter methods for the full lists
    fun getlUserCharacters(): LiveData<List<User_Characters>?>? {
        return lUserCharacters
    }

    fun getlUserInventory(): LiveData<List<User_Inventory>?>? {
        return lUserInventory
    }

    fun getlUserValues(): LiveData<List<User_Values>?>? {
        return lUserValues
    }

    fun getlUserCards(): LiveData<List<User_Cards>?>? {
        return lUserCards
    }

    fun getlUserDecks(): LiveData<List<User_Decks>?>? {
        return lUserDecks
    }

    fun getlUserEQPlayed(): LiveData<List<User_EQPlayed>?>? {
        return lUserEQPlayed
    }

    fun getCardNameSearchResults(): LiveData<List<User_Cards>?> {
        return cardNameSearchResults
    }

    //add wrapper for insert()
//MUST BE CALLED ON A NON-UI thread
    fun insert(userValues: User_Values) {
        insertAsyncTaskValues(userValuesDao).execute(userValues)
    }

    fun insert(userCharacters: User_Characters) {
        insertAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun insert(userCards: User_Cards) {
        insertAsyncTaskCards(userCardsDao).execute(userCards)
    }

    fun insert(userDecks: User_Decks) {
        insertAsyncTaskDecks(userDecksDao).execute(userDecks)
    }

    fun insert(userEQPlayed: User_EQPlayed) {
        insertAsyncTaskEQPlayed(userEQPlayedDao).execute(userEQPlayed)
    }

    fun insert(userInventory: User_Inventory) {
        insertAsyncTaskInventory(userInventoryDao).execute(userInventory)
    }

    fun updateFrontChar(userValues: User_Values) {
        updateFrontCharAsyncTaskValues(userValuesDao).execute(userValues)
    }

    fun updateRegion(userValues: User_Values) {
        updateRegionAsyncTaskValues(userValuesDao).execute(userValues)
    }

    fun updateOkane(userValues: User_Values) {
        updateOkaneAsyncTaskValues(userValuesDao).execute(userValues)
    }

    fun updatePassword(userValues: User_Values) {
        updatePasswordAsyncTaskValues(userValuesDao).execute(userValues)
    }

    fun updateUsername(userValues: User_Values) {
        updateUsernameAsyncTaskValues(userValuesDao).execute(userValues)
    }

    fun updatePhase(userValues: User_Values) {
        updatePhaseAsyncTaskValues(userValuesDao).execute(userValues)
    }

    fun updatePL(userValues: User_Values) {
        updatePLAsyncTaskValues(userValuesDao).execute(userValues)
    }

    fun updateLabel(userCharacters: User_Characters) {
        updateLabelAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateLevel(userCharacters: User_Characters) {
        updateLevelAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRank(userCharacters: User_Characters) {
        updateRankAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateWeapon(userCharacters: User_Characters) {
        updateWeaponAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateDeck(userCharacters: User_Characters) {
        updateDeckAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateItem(userCharacters: User_Characters) {
        updateItemAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion1exp(userCharacters: User_Characters) {
        updateRegion1expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion23exp(userCharacters: User_Characters) {
        updateRegion23expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion4exp(userCharacters: User_Characters) {
        updateRegion4expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion5exp(userCharacters: User_Characters) {
        updateRegion5expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion6exp(userCharacters: User_Characters) {
        updateRegion6expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion7exp(userCharacters: User_Characters) {
        updateRegion7expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion89exp(userCharacters: User_Characters) {
        updateRegion89expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion10exp(userCharacters: User_Characters) {
        updateRegion10expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion11exp(userCharacters: User_Characters) {
        updateRegion11expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion12exp(userCharacters: User_Characters) {
        updateRegion12expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion13exp(userCharacters: User_Characters) {
        updateRegion13expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion14exp(userCharacters: User_Characters) {
        updateRegion14expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion16exp(userCharacters: User_Characters) {
        updateRegion16expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion17exp(userCharacters: User_Characters) {
        updateRegion17expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion18exp(userCharacters: User_Characters) {
        updateRegion18expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion19exp(userCharacters: User_Characters) {
        updateRegion19expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateRegion20exp(userCharacters: User_Characters) {
        updateRegion20expAsyncTaskCharacters(userCharactersDao).execute(userCharacters)
    }

    fun updateAmount(userCards: User_Cards) {
        updateAmountAsyncTaskCards(userCardsDao).execute(userCards)
    }

    fun updatePosition(userCards: User_Cards) {
        updatePositionAsyncTaskCards(userCardsDao).execute(userCards)
    }

    fun updateLabel(userDecks: User_Decks) {
        updateLabelAsyncTaskDecks(userDecksDao).execute(userDecks)
    }

    fun updateChar(userDecks: User_Decks) {
        updateCharAsyncTaskDecks(userDecksDao).execute(userDecks)
    }

    fun updateLen(userDecks: User_Decks) {
        updateLenAsyncTaskDecks(userDecksDao).execute(userDecks)
    }

    fun updateCompleted(userEqPlayed: User_EQPlayed) {
        updateCompletedAsyncTaskEQPlayed(userEQPlayedDao).execute(userEqPlayed)
    }

    fun updateSigItem(userEqPlayed: User_EQPlayed) {
        updateSigItemAsyncTaskEQPlayed(userEQPlayedDao).execute(userEqPlayed)
    }

    fun updateAmount(userInventory: User_Inventory) {
        updateAmountAsyncTaskInventory(userInventoryDao).execute(userInventory)
    }

    fun deleteCard(userCards: User_Cards) {
        deleteAsyncTaskCards(userCardsDao).execute(userCards)
    }

    fun deleteDeck(userDecks: User_Decks) {
        deleteAsyncTaskDecks(userDecksDao).execute(userDecks)
    }

    fun deleteInventory(userInventory: User_Inventory) {
        deleteAsyncTaskInventory(userInventoryDao).execute(userInventory)
    }

    fun deleteEQPlayed(userEQPlayed: User_EQPlayed) {
        deleteAsyncTaskEQPlayed(userEQPlayedDao).execute(userEQPlayed)
    }

    //returns a list of Cards with this card name
    fun findNameCards(name: String) {
        getCardNameAsyncTaskCard(userCardsDao).execute(name)
    }

    //insertAsyncTask classes
    private class insertAsyncTaskValues internal constructor(private val mAsyncTaskDao: User_Values_Dao?) :
        AsyncTask<User_Values, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Values): Void? {
            mAsyncTaskDao!!.insert(params[0])
            return null
        }

    }

    private class insertAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.insert(params[0])
            return null
        }

    }

    private class insertAsyncTaskCards internal constructor(private val mAsyncTaskDao: User_Cards_Dao?) :
        AsyncTask<User_Cards, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Cards): Void? {
            mAsyncTaskDao!!.insert(params[0])
            return null
        }

    }

    private class insertAsyncTaskDecks internal constructor(private val mAsyncTaskDao: User_Decks_Dao?) :
        AsyncTask<User_Decks, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Decks): Void? {
            mAsyncTaskDao!!.insert(params[0])
            return null
        }

    }

    private class insertAsyncTaskEQPlayed internal constructor(private val mAsyncTaskDao: User_EQPlayed_Dao?) :
        AsyncTask<User_EQPlayed, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_EQPlayed): Void? {
            mAsyncTaskDao!!.insert(params[0])
            return null
        }

    }

    private class insertAsyncTaskInventory internal constructor(private val mAsyncTaskDao: User_Inventory_Dao?) :
        AsyncTask<User_Inventory, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Inventory): Void? {
            mAsyncTaskDao!!.insert(params[0])
            return null
        }

    }

    //deleteAsyncTask classes
    private class deleteAsyncTaskInventory internal constructor(private val mAsyncTaskDao: User_Inventory_Dao?) :
        AsyncTask<User_Inventory, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Inventory): Void? {
            mAsyncTaskDao!!.delete(params[0])
            return null
        }

    }

    private class deleteAsyncTaskCards internal constructor(private val mAsyncTaskDao: User_Cards_Dao?) :
        AsyncTask<User_Cards, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Cards): Void? {
            mAsyncTaskDao!!.delete(params[0])
            return null
        }

    }

    private class deleteAsyncTaskDecks internal constructor(private val mAsyncTaskDao: User_Decks_Dao?) :
        AsyncTask<User_Decks, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Decks): Void? {
            mAsyncTaskDao!!.delete(params[0])
            return null
        }

    }

    private class deleteAsyncTaskEQPlayed internal constructor(private val mAsyncTaskDao: User_EQPlayed_Dao?) :
        AsyncTask<User_EQPlayed, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_EQPlayed): Void? {
            mAsyncTaskDao!!.delete(params[0])
            return null
        }

    }

    //UPDATE VALUES:
//
    private class updateFrontCharAsyncTaskValues internal constructor(private val mAsyncTaskDao: User_Values_Dao?) :
        AsyncTask<User_Values, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Values): Void? {
            mAsyncTaskDao!!.updateFrontChar(params[0].front_char, params[0].id)
            return null
        }

    }

    private class updateRegionAsyncTaskValues internal constructor(private val mAsyncTaskDao: User_Values_Dao?) :
        AsyncTask<User_Values, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Values): Void? {
            mAsyncTaskDao!!.updateRegion(params[0].cur_region, params[0].id)
            return null
        }

    }

    //note:if you want to update the login, you must set the username, and then the password, using the new username as the key
    private class updateUsernameAsyncTaskValues internal constructor(private val mAsyncTaskDao: User_Values_Dao?) :
        AsyncTask<User_Values, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Values): Void? {
            mAsyncTaskDao!!.updateUsername(params[0].username, params[0].id)
            return null
        }

    }

    private class updatePasswordAsyncTaskValues internal constructor(private val mAsyncTaskDao: User_Values_Dao?) :
        AsyncTask<User_Values, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Values): Void? {
            mAsyncTaskDao!!.updatePassword(params[0].password, params[0].id)
            return null
        }

    }

    private class updateOkaneAsyncTaskValues internal constructor(private val mAsyncTaskDao: User_Values_Dao?) :
        AsyncTask<User_Values, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Values): Void? {
            mAsyncTaskDao!!.updateOkane(params[0].cur_okane, params[0].id)
            return null
        }

    }

    private class updatePhaseAsyncTaskValues internal constructor(private val mAsyncTaskDao: User_Values_Dao?) :
        AsyncTask<User_Values, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Values): Void? {
            mAsyncTaskDao!!.updatePhase(params[0].cur_phase, params[0].id)
            return null
        }

    }

    private class updatePLAsyncTaskValues internal constructor(private val mAsyncTaskDao: User_Values_Dao?) :
        AsyncTask<User_Values, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Values): Void? {
            mAsyncTaskDao!!.updatePL(params[0].cur_PL, params[0].id)
            return null
        }

    }

    //UPDATE CHARACTERS:
//
    private class updateLabelAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateLabel(params[0].name, params[0].id)
            return null
        }

    }

    private class updateLevelAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateLevel(params[0].level, params[0].id)
            return null
        }

    }

    private class updateRankAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRank(params[0].rank, params[0].id)
            return null
        }

    }

    private class updateWeaponAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateWeapon(params[0].weapon_equip, params[0].id)
            return null
        }

    }

    private class updateDeckAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateDeck(params[0].deck_equip, params[0].id)
            return null
        }

    }

    private class updateItemAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateItem(params[0].item_equip, params[0].id)
            return null
        }

    }

    private class updateRegion1expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion1exp(params[0].region1exp, params[0].id)
            return null
        }

    }

    private class updateRegion23expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion23exp(params[0].region23exp, params[0].id)
            return null
        }

    }

    private class updateRegion4expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion4exp(params[0].region4exp, params[0].id)
            return null
        }

    }

    private class updateRegion5expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion5exp(params[0].region5exp, params[0].id)
            return null
        }

    }

    private class updateRegion6expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion6exp(params[0].region6exp, params[0].id)
            return null
        }

    }

    private class updateRegion7expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion7exp(params[0].region7exp, params[0].id)
            return null
        }

    }

    private class updateRegion89expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion89exp(params[0].region89exp, params[0].id)
            return null
        }

    }

    private class updateRegion10expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion10exp(params[0].region10exp, params[0].id)
            return null
        }

    }

    private class updateRegion11expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion11exp(params[0].region11exp, params[0].id)
            return null
        }

    }

    private class updateRegion12expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion12exp(params[0].region12exp, params[0].id)
            return null
        }

    }

    private class updateRegion13expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion13exp(params[0].region13exp, params[0].id)
            return null
        }

    }

    private class updateRegion14expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion14exp(params[0].region14exp, params[0].id)
            return null
        }

    }

    private class updateRegion16expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion16exp(params[0].region16exp, params[0].id)
            return null
        }

    }

    private class updateRegion17expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion17exp(params[0].region17exp, params[0].id)
            return null
        }

    }

    private class updateRegion18expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion18exp(params[0].region18exp, params[0].id)
            return null
        }

    }

    private class updateRegion19expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion19exp(params[0].region19exp, params[0].id)
            return null
        }

    }

    private class updateRegion20expAsyncTaskCharacters internal constructor(private val mAsyncTaskDao: User_Characters_Dao?) :
        AsyncTask<User_Characters, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Characters): Void? {
            mAsyncTaskDao!!.updateRegion20exp(params[0].region20exp, params[0].id)
            return null
        }

    }

    //UPDATE CARDS:
//
    private class updateAmountAsyncTaskCards internal constructor(private val mAsyncTaskDao: User_Cards_Dao?) :
        AsyncTask<User_Cards, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Cards): Void? {
            mAsyncTaskDao!!.updateAmount(params[0].amount, params[0].id)
            return null
        }

    }

    private class updatePositionAsyncTaskCards internal constructor(private val mAsyncTaskDao: User_Cards_Dao?) :
        AsyncTask<User_Cards, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Cards): Void? {
            mAsyncTaskDao!!.updatePosition(params[0].position, params[0].id)
            return null
        }

    }

    //UPDATE DECK:
//
    private class updateLabelAsyncTaskDecks internal constructor(private val mAsyncTaskDao: User_Decks_Dao?) :
        AsyncTask<User_Decks, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Decks): Void? {
            mAsyncTaskDao!!.updateLabel(params[0].name, params[0].id)
            return null
        }

    }

    private class updateCharAsyncTaskDecks internal constructor(private val mAsyncTaskDao: User_Decks_Dao?) :
        AsyncTask<User_Decks, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Decks): Void? {
            mAsyncTaskDao!!.updateChar(params[0].char_equip, params[0].id)
            return null
        }

    }

    private class updateLenAsyncTaskDecks internal constructor(private val mAsyncTaskDao: User_Decks_Dao?) :
        AsyncTask<User_Decks, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Decks): Void? {
            mAsyncTaskDao!!.updateLen(params[0].length, params[0].id)
            return null
        }

    }

    //UPDATE EQPLAYED:
//
    private class updateCompletedAsyncTaskEQPlayed internal constructor(private val mAsyncTaskDao: User_EQPlayed_Dao?) :
        AsyncTask<User_EQPlayed, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_EQPlayed): Void? {
            mAsyncTaskDao!!.updateComp(params[0].completed, params[0].id)
            return null
        }

    }

    private class updateSigItemAsyncTaskEQPlayed internal constructor(private val mAsyncTaskDao: User_EQPlayed_Dao?) :
        AsyncTask<User_EQPlayed, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_EQPlayed): Void? {
            mAsyncTaskDao!!.updateSigItem(
                params[0].sig_item_collected,
                params[0].id
            )
            return null
        }

    }

    //UPDATE INVENTORY:
//
    private class updateAmountAsyncTaskInventory internal constructor(private val mAsyncTaskDao: User_Inventory_Dao?) :
        AsyncTask<User_Inventory, Void?, Void?>() {
        protected override fun doInBackground(vararg params: User_Inventory): Void? {
            mAsyncTaskDao!!.updateAmount(params[0].amount, params[0].id)
            return null
        }

    }

    //FIND CARDS:
//
    private class getCardNameAsyncTaskCard internal constructor(private val mAsyncTaskDao: User_Cards_Dao?) :
        AsyncTask<String, Void?, List<User_Cards?>?>() {
        private val delegate: RPG_RoomRepository? = null
        protected override fun doInBackground(vararg params: String): List<User_Cards?>? {
            return mAsyncTaskDao!!.getCardName(params[0]).value
        }

        override fun onPostExecute(result: List<User_Cards?>?) {
            delegate!!.getNameAsyncTaskCardFinished(result?.filterNotNull())
        }

    }

    private fun getNameAsyncTaskCardFinished(results: List<User_Cards>?) {

        cardNameSearchResults.value = results
    }

    init {
        val db: RPG_RoomDatabase = RPG_RoomDatabase.getDatabase(application)!!
        userValuesDao = db.UserValuesDao()!!
        userCharactersDao = db.UserCharactersDao()!!
        userInventoryDao = db.UserInventoryDao()!!
        userCardsDao = db.UserCardsDao()!!
        userDecksDao = db.UserDecksDao()!!
        userEQPlayedDao = db.UserEQPlayedDao()!!
        lUserValues = userValuesDao!!.getAlphabetizedUser_Values()!!
        println("inside repostiory: $lUserValues")
        lUserCharacters = userCharactersDao.all
        lUserCards = userCardsDao!!.getAll()
        lUserDecks = userDecksDao!!.getAll()
        lUserInventory = userInventoryDao!!.getAll()
        lUserEQPlayed = userEQPlayedDao!!.getAll()
    }
}