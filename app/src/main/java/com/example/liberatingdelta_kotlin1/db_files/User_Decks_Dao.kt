package com.example.liberatingdelta_kotlin1.db_files

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface User_Decks_Dao {
    //The DAO associated with the User_Decks_Table
//add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userDecks: User_Decks?)

    @Delete
    fun delete(userDecks: User_Decks?)

    //to update a field with a matching deck
    @Update
    fun update(userDecks: User_Decks?)

    //update LABEL by id
    @Query("UPDATE User_Decks_Table SET LABEL=:name WHERE id=:index")
    fun updateLabel(name: String?, index: Int)

    //update CHAR by id
    @Query("UPDATE User_Decks_Table SET CHAR=:char_equip WHERE id=:index")
    fun updateChar(char_equip: String?, index: Int)

    //update LEN by id
    @Query("UPDATE User_Decks_Table SET LEN=:length WHERE id=:index")
    fun updateLen(length: Int, index: Int)

    //delete the entire table
    @Query("DELETE FROM User_Decks_Table")
    fun deleteAll()

    //return list of UserDecks
    @Query("SELECT * FROM User_Decks_Table ORDER BY LABEL ASC")
    fun getAll(): LiveData<List<User_Decks>?>? /*
    //return inventory name by id
    @Query("SELECT LABEL FROM User_Decks_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //return the amt of uses left
    @Query("SELECT LEN FROM User_Decks_Table ORDER BY id ASC")
    LiveData<List<Integer>> getLens();

    //returns the characters equipping that deck
    @Query("SELECT CHAR FROM User_Decks_Table ORDER BY id ASC")
    LiveData<List<String>> getChars();
     */
}