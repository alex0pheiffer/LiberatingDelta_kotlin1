package com.example.liberatingdelta_kotlin1.db_files

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface User_Cards_Dao {
    //The DAO associated with the User_Cards table
//add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userCards: User_Cards?)

    //to update a field with a matching user
    @Delete
    fun delete(userCards: User_Cards?)

    //delete the entire table
    @Query("DELETE FROM User_Cards_Table")
    fun deleteAll()

    //return User_Cards object list
    @Query("SELECT * FROM User_Cards_Table ORDER BY LABEL, DECK ASC")
    fun getAll(): LiveData<List<User_Cards>?>?

    //returns a list of User_Cards that have the deck 'cardName'
    @Query("SELECT * FROM User_Cards_Table WHERE LABEL =:cardName")
    fun getCardName(cardName: String?): LiveData<List<User_Cards?>?>

    //updating the AMT by order id
    @Query("UPDATE User_Cards_Table SET AMT=:amount WHERE id =:index")
    fun updateAmount(amount: Int, index: Int)

    //updating the POS by order id
//please note if you want to change decks, create a new card entity
    @Query("UPDATE User_Cards_Table SET POS=:position WHERE id=:index")
    fun updatePosition(position: Int, index: Int) /*
    //returns a whole list of LABELs (the name of the card)
    @Query("SELECT LABEL FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //returns a whole list of AMTs (how many of this card is in the user's inventory)
    @Query("SELECT AMT FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<Integer>> getAmts();

    //returns a whole list of DECKs
    @Query("SELECT DECK FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<String>> getDecks();

    //returns a whole list of POSs (Position of the card in the deck)
    @Query("SELECT POS FROM User_Cards_Table ORDER BY id ASC")
    LiveData<List<Integer>> getPoss();
    */
}