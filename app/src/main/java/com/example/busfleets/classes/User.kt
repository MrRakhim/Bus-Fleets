package com.example.busfleets.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "kerekEmesId") val kerekEmesId: Int? = null,
        @ColumnInfo(name = "id") val id: String,
        @ColumnInfo(name = "email") val email: String,
        @ColumnInfo(name = "username") val username: String
)
