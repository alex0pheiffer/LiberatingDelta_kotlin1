package com.example.liberatingdelta_kotlin1.db_files

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface User_EQPlayed_Dao {
    //The DAO associated with the User_EQPlayed_Table
//add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(UserEQPlayed: User_EQPlayed?)

    //to update a field with a matching EqPlayed
    @Update
    fun update(UserEQPlayed: User_EQPlayed?)

    //update COMP by id
    @Query("UPDATE User_EQPlayed_Table SET COMP=:completed WHERE id=:index")
    fun updateComp(completed: Int, index: Int)

    //update SIGITEM by id
    @Query("UPDATE User_EQPlayed_Table SET SIGITEM=:sig_item_collected WHERE id=:index")
    fun updateSigItem(sig_item_collected: Int, index: Int)

    @Delete
    fun delete(UserEQPlayed: User_EQPlayed?)

    //delete the entire table
    @Query("DELETE FROM User_EQPlayed_Table")
    fun deleteAll()

    //return the list of User_EQPlayed objects
    @Query("SELECT * FROM User_EQPlayed_Table ORDER BY id ASC")
    fun getAll(): LiveData<List<User_EQPlayed>?>? /*
    //return the list of all LABELs
    @Query("SELECT LABEL FROM User_EQPlayed_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //returns a whole list of COMPs
    @Query("SELECT COMP FROM User_EQPlayed_Table ORDER BY id ASC")
    LiveData<List<Integer>> getComps();

    //returns a whole list of SIGITEMs
    @Query("SELECT SIGITEM FROM User_EQPlayed_Table ORDER BY id ASC")
    LiveData<List<Integer>> getSigItems();
     */
}