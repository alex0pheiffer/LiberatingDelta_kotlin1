package com.example.liberatingdelta_kotlin1.db_files

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface User_Values_Dao {
    //The DAO associated with the User_Values table
//add a new row to the table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userValues: User_Values?)

    //to update a field with a matching user
    @Update
    fun update(userValues: User_Values?)

    //update USERNAME by USERNAME
    @Query("UPDATE User_Values_Table SET USERNAME=:username WHERE id=:index")
    fun updateUsername(username: String?, index: Int)

    //update PASSWORD by USERNAME
    @Query("UPDATE User_Values_Table SET PASSWORD=:password WHERE id=:index")
    fun updatePassword(password: String?, index: Int)

    //update OKANE by USERNAME
    @Query("UPDATE User_Values_Table SET OKANE=:cur_okane WHERE id=:index")
    fun updateOkane(cur_okane: Int, index: Int)

    //update PHASE by USERNAME
    @Query("UPDATE User_Values_Table SET PHASE=:cur_phase WHERE id=:index")
    fun updatePhase(cur_phase: Int, index: Int)

    //update PL by USERNAME
    @Query("UPDATE User_Values_Table SET PL=:cur_PL WHERE id=:index")
    fun updatePL(cur_PL: Int, index: Int)

    //update FRONTCHAR by USERNAME
    @Query("UPDATE User_Values_Table SET FRONTCHAR=:front_char WHERE id=:index")
    fun updateFrontChar(front_char: String?, index: Int)

    //update REGION by USERNAME
    @Query("UPDATE User_Values_Table SET REGION=:cur_region WHERE id=:index")
    fun updateRegion(cur_region: String?, index: Int)

    //delete the entire table
    @Query("DELETE FROM User_Values_Table")
    fun deleteAll()

    //return the list of all the usernames
    @Query("SELECT USERNAME FROM User_Values_Table")
    fun getUsernames(): LiveData<List<String>?>?

    //alphabetize the rows by username
    @Query("SELECT * FROM User_Values_Table ORDER BY username ASC")
    fun getAlphabetizedUser_Values(): LiveData<List<User_Values>?>?
}