package com.example.busfleets

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.busfleets.Adapters.TripAdapter
import com.example.busfleets.DAOs.AppDAO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView(){

            add_button.setOnClickListener {
                if (auth.currentUser != null){
                    val goAddTrip = Intent(this@MainActivity, AddTripActivity::class.java)
                    startActivity(goAddTrip)
                }
                else{
                    Toast.makeText(this, "You must log in to add a trip", Toast.LENGTH_LONG).show()
                }
            }


        list_view.layoutManager = LinearLayoutManager(this@MainActivity)

        AsyncTask.execute {
            val trips = AppDatabase.getInstance(applicationContext)!!
                .getAppDAO().getTrips()
            runOnUiThread{
                list_view.adapter = TripAdapter(trips, { trip ->
                    startActivity(Intent(this@MainActivity, Passengers::class.java))
                    Log.d("trip", trip.toString())
                }){ id ->
                    AsyncTask.execute {
                        if (id != null) {
                            AppDatabase.getInstance(applicationContext)!!
                                    .getAppDAO().deleteTrip(id)
                        }
                    }
                }
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if(auth?.currentUser != null){
            menuInflater.inflate(R.menu.menu, menu)
            menu?.removeItem(R.id.login_button)
            menu?.removeItem(R.id.register_button)

        }else{
            menuInflater.inflate(R.menu.menu, menu)
            menu?.removeItem(R.id.logout_button)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item?.itemId){
            R.id.logout_button ->{
                auth.signOut()
                var refresh =  Intent(this, MainActivity::class.java)
                startActivity(refresh)
                true
            }
            R.id.login_button -> {
                var login =  Intent(this, Login::class.java)
                startActivity(login)
                true
            }
            R.id.register_button -> {
                var login =  Intent(this, RegisterActivity::class.java)
                startActivity(login)
                true
            }
            else-> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}