package com.example.mychallenge3.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "photo")
    val photo: String,
)
