package com.example.liberatingdelta_kotlin1.db_files

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//entities that belong in the database and set the version number.
// Listing the entities will create tables in the database.
//YOU MUST UPDATE THE VERSION NUMBER IF YOU MODIFY DB SCHEMA, AND CHANGE THE MIGRATION
//https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
@Database(
    entities = [User_Values::class, User_Characters::class, User_EQPlayed::class, User_Inventory::class, User_Cards::class, User_Decks::class],
    version = 2
)
abstract class RPG_RoomDatabase : RoomDatabase() {
//abstract getter for each DAO
abstract fun UserValuesDao(): User_Values_Dao?

abstract fun UserEQPlayedDao(): User_EQPlayed_Dao?
abstract fun UserCharactersDao(): User_Characters_Dao?
abstract fun UserDecksDao(): User_Decks_Dao?
abstract fun UserInventoryDao(): User_Inventory_Dao?
abstract fun UserCardsDao(): User_Cards_Dao?

companion object {
    private val forMessaging: Context? = null
    //the following is to prevent multiple instances of the db from being created
    @Volatile
    private var INSTANCE: RPG_RoomDatabase? = null

    fun getDatabase(context: Context): RPG_RoomDatabase? { //updateRequire
    //forMessaging = context;
    //sendMessage("in database",context);
    //INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
    //       RPG_RoomDatabase.class, "rpg_database").addMigrations(MIGRATION_1_2).build();
    //sendMessage("INSTANCE mitigated, now removing",context);
    //context.deleteDatabase("rpg_database");
    //System.out.println("Database deleted");
    //sendMessage("Database deleted",context);
    if (INSTANCE == null) {
        synchronized(RPG_RoomDatabase::class.java) {
            if (INSTANCE == null) { //normal
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RPG_RoomDatabase::class.java, "rpg_database"
                ).addCallback(sRoomDatabaseCallback).build()
                //sendMessage("Database created; ver: ",context);
            }
        }
    }
    return INSTANCE
    }

    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            //sendMessage("mitigationTest ver: "+database.getVersion(),forMessaging);
            // Since we didn't alter the table, there's nothing else to do here.
            //IF YOU DO MODIFY, EX:
            //adding columns: https://medium.com/@manuelvicnt/android-room-upgrading-alpha-versions-needs-a-migration-with-kotlin-or-nonnull-7a2d140f05b9
            /*
                        database.execSQL("ALTER TABLE User_Values_Table"
                        + " ADD COLUMN REGION TEXT NOT NULL");
            */
            //create new table
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS TABLE_NAME_TEMP " +
                        "(`id` INTEGER NOT NULL PRIMARY KEY, " +
                        "`LABEL` TEXT NOT NULL, " +
                        "`LVL` INTEGER NOT NULL, " +
                        "`RANK` INTEGER NOT NULL, " +
                        "`WEAPON` TEXT NOT NULL, " +
                        "`DECK` TEXT NOT NULL, " +
                        "`ITEM` TEXT NOT NULL, " +
                        "`REGION1EXP` INTEGER NOT NULL, " +
                        "`REGION23EXP` INTEGER NOT NULL, " +
                        "`REGION4EXP` INTEGER NOT NULL, " +
                        "`REGION5EXP` INTEGER NOT NULL, " +
                        "`REGION6EXP` INTEGER NOT NULL, " +
                        "`REGION7EXP` INTEGER NOT NULL, " +
                        "`REGION89EXP` INTEGER NOT NULL, " +
                        "`REGION10EXP` INTEGER NOT NULL, " +
                        "`REGION11EXP` INTEGER NOT NULL, " +
                        "`REGION12EXP` INTEGER NOT NULL, " +
                        "`REGION13EXP` INTEGER NOT NULL, " +
                        "`REGION14EXP` INTEGER NOT NULL, " +
                        "`REGION16EXP` INTEGER NOT NULL, " +
                        "`REGION17EXP` INTEGER NOT NULL, " +
                        "`REGION18EXP` INTEGER NOT NULL, " +
                        "`REGION19EXP` INTEGER NOT NULL, " +
                        "`REGION20EXP` INTEGER NOT NULL)"
            )
            //copy the data
            /*
            database.execSQL("INSERT INTO TABLE_NAME_TEMP (LABEL, LVL, RANK, WEAPON, DECK, ITEM) "
                    + "SELECT LABEL, LVL, EXP, WEAPON, DECK, ITEM"
                    + "FROM User_Characters_Table");
            */
            //drop table
            database.execSQL("DROP TABLE User_Characters_Table")
            //replace table name
            database.execSQL("ALTER TABLE TABLE_NAME_TEMP RENAME TO User_Characters_Table")
        }
    }
    private val sRoomDatabaseCallback: RoomDatabase.Callback = object : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {}
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            //sendMessage("db Opened: ver: "+db.getVersion(),forMessaging);
            println("DB HAS BEEN OPENED")
            PopulateDbAsync(INSTANCE).execute()
            //sendMessage("populated db",forMessaging);
        }
    }
    /*
    public static void sendMessage(String message, Context context){
    Intent intent = new Intent();
    intent.setClassName("com.example.twoactivitycrash", "com.example.twoactivitycrash.MyBroadcastReceiver");
    intent.setAction("com.example.twoactivitycrash.MyBroadcastReceiver");
    intent.putExtra("MESSAGE_A", message);
    context.sendBroadcast(intent);
    }
    */
}
}