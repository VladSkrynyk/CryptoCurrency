package com.example.cryptocurrency.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [CryptoEntity::class], version = 1)
public abstract class CryptoItemsRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): DaoCryptoItem

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CryptoItemsRoomDatabase? = null

        fun getDatabase(context: Context): CryptoItemsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let { return it }  // if (INSTANCE != null) return@synchronized INSTANCE!!
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoItemsRoomDatabase::class.java,
                    "release_database"
                )
//                    .allowMainThreadQueries()  // allows request from DB in main thread
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}