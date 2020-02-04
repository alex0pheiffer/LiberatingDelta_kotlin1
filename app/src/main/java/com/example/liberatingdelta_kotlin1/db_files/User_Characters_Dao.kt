package com.example.liberatingdelta_kotlin1.db_files

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface User_Characters_Dao {
    //The DAO associated with the User_Characters_Table
//add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userCharacters: User_Characters?)

    //to update a field with a matching user
    @Update
    fun update(userCharacters: User_Characters?)

    //update LABEL by id
    @Query("UPDATE User_Characters_Table SET LABEL=:name WHERE id=:index")
    fun updateLabel(name: String?, index: Int)

    //update LVL by id
    @Query("UPDATE User_Characters_Table SET LVL=:exp WHERE id=:index")
    fun updateLevel(exp: Int, index: Int)

    //update RANK by id
    @Query("UPDATE User_Characters_Table SET RANK=:exp WHERE id=:index")
    fun updateRank(exp: Int, index: Int)

    //update WEAPON by id
    @Query("UPDATE User_Characters_Table SET WEAPON=:weapon_equip WHERE id=:index")
    fun updateWeapon(weapon_equip: String?, index: Int)

    //update DECK by id
    @Query("UPDATE User_Characters_Table SET DECK=:deck_equip WHERE id=:index")
    fun updateDeck(deck_equip: String?, index: Int)

    //update ITEM by id
    @Query("UPDATE User_Characters_Table SET ITEM=:item_equip WHERE id=:index")
    fun updateItem(item_equip: String?, index: Int)

    //update REGION1EXP by id
    @Query("UPDATE User_Characters_Table SET REGION1EXP=:exp WHERE id=:index")
    fun updateRegion1exp(exp: Int, index: Int)

    //update REGION23EXP by id
    @Query("UPDATE User_Characters_Table SET REGION23EXP=:exp WHERE id=:index")
    fun updateRegion23exp(exp: Int, index: Int)

    //update REGION4EXP by id
    @Query("UPDATE User_Characters_Table SET REGION4EXP=:exp WHERE id=:index")
    fun updateRegion4exp(exp: Int, index: Int)

    //update REGION5EXP by id
    @Query("UPDATE User_Characters_Table SET REGION5EXP=:exp WHERE id=:index")
    fun updateRegion5exp(exp: Int, index: Int)

    //update REGION6EXP by id
    @Query("UPDATE User_Characters_Table SET REGION6EXP=:exp WHERE id=:index")
    fun updateRegion6exp(exp: Int, index: Int)

    //update REGION7EXP by id
    @Query("UPDATE User_Characters_Table SET REGION7EXP=:exp WHERE id=:index")
    fun updateRegion7exp(exp: Int, index: Int)

    //update REGION89EXP by id
    @Query("UPDATE User_Characters_Table SET REGION89EXP=:exp WHERE id=:index")
    fun updateRegion89exp(exp: Int, index: Int)

    //update REGION10EXP by id
    @Query("UPDATE User_Characters_Table SET REGION10EXP=:exp WHERE id=:index")
    fun updateRegion10exp(exp: Int, index: Int)

    //update REGION11EXP by id
    @Query("UPDATE User_Characters_Table SET REGION11EXP=:exp WHERE id=:index")
    fun updateRegion11exp(exp: Int, index: Int)

    //update REGION12EXP by id
    @Query("UPDATE User_Characters_Table SET REGION12EXP=:exp WHERE id=:index")
    fun updateRegion12exp(exp: Int, index: Int)

    //update REGION13EXP by id
    @Query("UPDATE User_Characters_Table SET REGION13EXP=:exp WHERE id=:index")
    fun updateRegion13exp(exp: Int, index: Int)

    //update REGION14EXP by id
    @Query("UPDATE User_Characters_Table SET REGION14EXP=:exp WHERE id=:index")
    fun updateRegion14exp(exp: Int, index: Int)

    //update REGION16EXP by id
    @Query("UPDATE User_Characters_Table SET REGION16EXP=:exp WHERE id=:index")
    fun updateRegion16exp(exp: Int, index: Int)

    //update REGION17EXP by id
    @Query("UPDATE User_Characters_Table SET REGION17EXP=:exp WHERE id=:index")
    fun updateRegion17exp(exp: Int, index: Int)

    //update REGION18EXP by id
    @Query("UPDATE User_Characters_Table SET REGION18EXP=:exp WHERE id=:index")
    fun updateRegion18exp(exp: Int, index: Int)

    //update REGION19EXP by id
    @Query("UPDATE User_Characters_Table SET REGION19EXP=:exp WHERE id=:index")
    fun updateRegion19exp(exp: Int, index: Int)

    //update REGION20EXP by id
    @Query("UPDATE User_Characters_Table SET REGION20EXP=:exp WHERE id=:index")
    fun updateRegion20exp(exp: Int, index: Int)

    //delete the entire table
    @Query("DELETE FROM User_Characters_Table")
    fun deleteAll()

    //return the list USer_Characters
    /*
    //return the list of all LABELs
    @Query("SELECT LABEL FROM User_Characters_Table ORDER BY id ASC")
    fun getLabels() : LiveData<List<String>?>?

    //returns a whole list of LEVELs
    @Query("SELECT LVL FROM User_Characters_Table ORDER BY id ASC")
    fun getLvls() : LiveData<List<Integer>?>?

    //returns a whole list of EXPs (% comp after that level)
    @Query("SELECT EXP FROM User_Characters_Table ORDER BY id ASC")
    getExps() : LiveData<List<Integer>?>?

    //returns a whole list of WEPAONs
    @Query("SELECT WEAPON FROM User_Characters_Table ORDER BY id ASC")
    fun getWeapons() : LiveData<List<String>?>?

    //returns a whole list of DECKs
    @Query("SELECT DECK FROM User_Characters_Table ORDER BY id ASC")
    fun getDecks() : LiveData<List<String>?>?

    //returns a whole list of ITEMs
    @Query("SELECT ITEM FROM User_Characters_Table ORDER BY id ASC")
    fun getItems() : LiveData<List<String>?>?
    */

    @get:Query("SELECT * FROM User_Characters_Table ORDER BY id ASC")
    val all: LiveData<List<User_Characters>?>?
}