package com.example.busfleets.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "fromDate") val fromDate: String,
    @ColumnInfo(name = "toDate") val toDate: String,
    @ColumnInfo(name = "fromTime") val fromTime: String,
    @ColumnInfo(name = "toTime") val toTime: String,
    @ColumnInfo(name = "departure") val departure: String,
    @ColumnInfo(name = "arrival") val arrival: String,
    @ColumnInfo(name = "driverId") val driver_id: String,
    @ColumnInfo(name = "car_number") val car_number: String,
    @ColumnInfo(name = "car_seat") val car_seat: Int,
    @ColumnInfo(name = "car_mark") val car_mark: String
)
data class TripList (
    val artists: List<Trip>
)