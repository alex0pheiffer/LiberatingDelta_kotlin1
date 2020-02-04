package com.example.liberatingdelta_kotlin1.db_files

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface User_Inventory_Dao {
    //The DAO associated with the User_Inventory table
//add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userInventory: User_Inventory?)

    //removes an item
    @Delete
    fun delete(userInventory: User_Inventory?)

    //to update a field with a matching user
    @Update
    fun update(userInventory: User_Inventory?)

    //update AMT by id
    @Query("UPDATE User_Inventory_Table SET AMT=:amount WHERE id=:index")
    fun updateAmount(amount: Int, index: Int)

    //delete the entire table
    @Query("DELETE FROM User_Inventory_Table")
    fun deleteAll()

    //return User_Inventory object list
    @Query("SELECT * FROM User_Inventory_Table ORDER BY id ASC")
    fun getAll(): LiveData<List<User_Inventory>?>? /*
    //return inventory name by id
    @Query("SELECT LABEL FROM User_Inventory_Table ORDER BY id ASC")
    LiveData<List<String>> getLabels();

    //return the amt of uses left
    @Query("SELECT AMT FROM User_Inventory_Table ORDER BY id ASC")
    LiveData<List<Integer>> getAmts();

    //returns the type of item
    @Query("SELECT TYPE FROM User_Inventory_Table ORDER BY id ASC")
    LiveData<List<String>> getTypes();
     */
}