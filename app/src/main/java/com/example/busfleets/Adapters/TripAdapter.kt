package com.example.busfleets.Adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.busfleets.R
import com.example.busfleets.classes.Trip
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_item.view.*

class TripAdapter (
    private val trips: List<Trip>,
    private val onTripClicked: (Trip) -> Unit,
    private val deleteTripClicked: (Int?) -> Unit
): RecyclerView.Adapter<TripAdapter.TripViewHolder>() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder =
        TripViewHolder(
            LayoutInflater.from(parent.context!!)
                .inflate(R.layout.layout_item, parent, false)
        )

    override fun getItemCount(): Int = trips.size

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.bindTrips(trips[position])
    }

    inner class TripViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindTrips(trip: Trip) {

            Picasso.with(itemView.context).load(R.drawable.bus).into(view.bus_img)

            view.fromDate.text = "Дата - " + trip.fromDate
            view.fromTime.text = "Время - " + trip.fromTime
            view.toDate.text = "Дата - " + trip.toDate
            view.toTime.text = "Время - " + trip.toTime
            view.car_mark.text = trip.car_mark
            view.car_number.text = trip.car_number
            view.car_seat.text = trip.car_seat.toString()
            view.trip_destination.text = trip.departure + " - " + trip.arrival
            if (auth.currentUser?.uid == trip.driver_id) {
                view.delete_trip_btn.setOnClickListener {
                    deleteTripClicked(trip.id!!)
                }
            }else{
                view.delete_trip_btn.isVisible = false
            }
            view.setOnClickListener {
                onTripClicked(trip)
            }
        }
    }
}

