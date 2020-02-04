package com.example.liberatingdelta_kotlin1.db_files

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class RPG_ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RPG_RoomRepository
    private val lUserValues: LiveData<List<User_Values>?>?
    private val lUserCharacters: LiveData<List<User_Characters>?>?
    private val lUserInventory: LiveData<List<User_Inventory>?>?
    private val lUserCards: LiveData<List<User_Cards>?>?
    private val lUserDecks: LiveData<List<User_Decks>?>?
    private val lUserEQPlayed: LiveData<List<User_EQPlayed>?>?
    val nameCards: LiveData<List<User_Cards>?>?
    fun getlUserEQPlayed(): LiveData<List<User_EQPlayed>?>? {
        return lUserEQPlayed
    }

    fun getlUserDecks(): LiveData<List<User_Decks>?>? {
        return lUserDecks
    }

    fun getlUserCards(): LiveData<List<User_Cards>?>? {
        return lUserCards
    }

    fun getlUserValues(): LiveData<List<User_Values>?>? {
        return lUserValues
    }

    fun getlUserInventory(): LiveData<List<User_Inventory>?>? {
        return lUserInventory
    }

    fun getlUserCharacters(): LiveData<List<User_Characters>?>? {
        return lUserCharacters
    }

    //wrapper for repository's insert method so the insert is completely hidden from the UI
    fun insert(userValues: User_Values) {
        repository.insert(userValues)
    }

    fun insert(userInventory: User_Inventory) {
        repository.insert(userInventory)
    }

    fun insert(userDecks: User_Decks) {
        repository.insert(userDecks)
    }

    fun insert(userEQPlayed: User_EQPlayed) {
        repository.insert(userEQPlayed)
    }

    fun insert(userCharacters: User_Characters) {
        repository.insert(userCharacters)
    }

    fun insert(userCards: User_Cards) {
        repository.insert(userCards)
    }

    fun deleteCard(userCards: User_Cards) {
        repository.deleteCard(userCards)
    }

    fun deleteDeck(userDecks: User_Decks) {
        repository.deleteDeck(userDecks)
    }

    fun deleteInventory(userInventory: User_Inventory) {
        repository.deleteInventory(userInventory)
    }

    fun deleteEQPlayed(userEQPlayed: User_EQPlayed) {
        repository.deleteEQPlayed(userEQPlayed)
    }

    fun updateFrontChar(userValues: User_Values) {
        repository.updateFrontChar(userValues)
    }

    fun updateOkane(userValues: User_Values) {
        repository.updateOkane(userValues)
    }

    fun updatePassword(userValues: User_Values) {
        repository.updatePassword(userValues)
    }

    fun updateUsername(userValues: User_Values) {
        repository.updateUsername(userValues)
    }

    fun updatePhase(userValues: User_Values) {
        repository.updatePhase(userValues)
    }

    fun updatePL(userValues: User_Values) {
        repository.updatePL(userValues)
    }

    fun updateRegion(userValues: User_Values) {
        repository.updateRegion(userValues)
    }

    fun updateLabel(userCharacters: User_Characters) {
        repository.updateLabel(userCharacters)
    }

    fun updateLevel(userCharacters: User_Characters) {
        repository.updateLevel(userCharacters)
    }

    fun updateRank(userCharacters: User_Characters) {
        repository.updateRank(userCharacters)
    }

    fun updateWeapon(userCharacters: User_Characters) {
        repository.updateWeapon(userCharacters)
    }

    fun updateDeck(userCharacters: User_Characters) {
        repository.updateDeck(userCharacters)
    }

    fun updateItem(userCharacters: User_Characters) {
        repository.updateItem(userCharacters)
    }

    fun updateRegion1exp(userCharacters: User_Characters) {
        repository.updateRegion1exp(userCharacters)
    }

    fun updateRegion23exp(userCharacters: User_Characters) {
        repository.updateRegion23exp(userCharacters)
    }

    fun updateRegion4exp(userCharacters: User_Characters) {
        repository.updateRegion4exp(userCharacters)
    }

    fun updateRegion5exp(userCharacters: User_Characters) {
        repository.updateRegion5exp(userCharacters)
    }

    fun updateRegion6exp(userCharacters: User_Characters) {
        repository.updateRegion6exp(userCharacters)
    }

    fun updateRegion7exp(userCharacters: User_Characters) {
        repository.updateRegion7exp(userCharacters)
    }

    fun updateRegion89exp(userCharacters: User_Characters) {
        repository.updateRegion89exp(userCharacters)
    }

    fun updateRegion10exp(userCharacters: User_Characters) {
        repository.updateRegion10exp(userCharacters)
    }

    fun updateRegion11exp(userCharacters: User_Characters) {
        repository.updateRegion11exp(userCharacters)
    }

    fun updateRegion12exp(userCharacters: User_Characters) {
        repository.updateRegion12exp(userCharacters)
    }

    fun updateRegion13exp(userCharacters: User_Characters) {
        repository.updateRegion13exp(userCharacters)
    }

    fun updateRegion14exp(userCharacters: User_Characters) {
        repository.updateRegion14exp(userCharacters)
    }

    fun updateRegion16exp(userCharacters: User_Characters) {
        repository.updateRegion16exp(userCharacters)
    }

    fun updateRegion17exp(userCharacters: User_Characters) {
        repository.updateRegion17exp(userCharacters)
    }

    fun updateRegion18exp(userCharacters: User_Characters) {
        repository.updateRegion18exp(userCharacters)
    }

    fun updateRegion19exp(userCharacters: User_Characters) {
        repository.updateRegion19exp(userCharacters)
    }

    fun updateRegion20exp(userCharacters: User_Characters) {
        repository.updateRegion20exp(userCharacters)
    }

    fun updateAmount(userCards: User_Cards) {
        repository.updateAmount(userCards)
    }

    fun updatePosition(userCards: User_Cards) {
        repository.updatePosition(userCards)
    }

    fun updateLabel(userDecks: User_Decks) {
        repository.updateLabel(userDecks)
    }

    fun updateChar(userDecks: User_Decks) {
        repository.updateChar(userDecks)
    }

    fun updateLen(userDecks: User_Decks) {
        repository.updateLen(userDecks)
    }

    fun updateCompleted(userEqPlayed: User_EQPlayed) {
        repository.updateCompleted(userEqPlayed)
    }

    fun updateSigItem(userEqPlayed: User_EQPlayed) {
        repository.updateSigItem(userEqPlayed)
    }

    fun updateAmount(userInventory: User_Inventory) {
        repository.updateAmount(userInventory)
    }

    fun findNameCards(string: String) {
        repository.findNameCards(string)
    }

    init {
        repository = RPG_RoomRepository(application)
        lUserValues = repository.getlUserValues()
        println("inside viewModel: $lUserValues")
        lUserCharacters = repository.getlUserCharacters()
        lUserDecks = repository.getlUserDecks()
        lUserCards = repository.getlUserCards()
        lUserInventory = repository.getlUserInventory()
        lUserEQPlayed = repository.getlUserEQPlayed()
        nameCards = repository.cardNameSearchResults
    }
}