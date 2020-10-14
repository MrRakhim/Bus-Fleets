package com.example.busfleets

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.busfleets.classes.Trip
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add_trip.*
import kotlinx.android.synthetic.main.layout_item.*

class AddTripActivity : AppCompatActivity() {
    companion object{
    }
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trip)
        setupView()
    }

    private fun setupView(){
        val user_id = auth.currentUser!!.uid

        insert_save_button.setOnClickListener {
            val departure = departure_insert.text.toString()
            val arrival = arrival_insert.text.toString()
            val from_date = departure_date_insert.text.toString()
            val from_time = departure_time_insert.text.toString()
            val to_date = arrival_date_insert.text.toString()
            val to_time = arrival_time_insert.text.toString()
            val car_mark = car_mark_insert.text.toString()
            val car_seat = car_seat_insert.text.toString().toInt()
            val car_number = car_number_insert.text.toString()
            val trip = Trip(
                departure = departure,
                arrival = arrival,
                fromDate = from_date,
                fromTime = from_time,
                toDate = to_date,
                toTime = to_time,
                car_mark = car_mark,
                car_number = car_number,
                car_seat = car_seat,
                driver_id = user_id
            )
            AsyncTask.execute {
                AppDatabase.getInstance(applicationContext)!!
                    .getAppDAO().insertTrip(trip)
                runOnUiThread{
                    Toast.makeText(this, "Trip added succesfully!", Toast.LENGTH_LONG).show()
                    val gotomain = Intent(this@AddTripActivity, MainActivity::class.java)
                    startActivity(gotomain)
                }
            }
        }
    }
}