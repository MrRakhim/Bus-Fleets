package com.example.busfleets.DAOs
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.busfleets.classes.Trip
import com.example.busfleets.classes.User

@Dao
interface AppDAO {

    @Insert
    fun insertTrip(trip: Trip)

    @Query("Select * from trips")
    fun getTrips(): List<Trip>

    @Query("Delete from trips where id = :id")
    fun deleteTrip(id : Int)

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM users where id = :id")
    fun getUser(id: Int): User

}