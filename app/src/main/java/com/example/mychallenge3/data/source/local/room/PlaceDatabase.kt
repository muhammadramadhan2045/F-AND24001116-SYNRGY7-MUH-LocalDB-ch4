package com.example.mychallenge3.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mychallenge3.data.source.local.entity.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1)
abstract class PlaceDatabase :RoomDatabase() {
    abstract fun placeDao(): PlaceDao

    companion object {
        private var INSTANCE: PlaceDatabase? = null

        fun getInstance(context: Context): PlaceDatabase {
            if (INSTANCE == null) {
                synchronized(PlaceDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlaceDatabase::class.java,
                        "place.db"
                    ).build()
                }
            }
            return INSTANCE as PlaceDatabase
        }
    }
}