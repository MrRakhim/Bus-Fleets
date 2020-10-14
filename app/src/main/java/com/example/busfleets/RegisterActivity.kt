package com.example.busfleets

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.busfleets.classes.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupView()

    }
    private fun setupView(){
        sign_up_button.setOnClickListener {
            val email = email_input_r.text.toString()
            val username = username_input_r.text.toString()
            val password = password_input_r.text.toString()

            signUp(email = email, password = password, username = username)

        }
    }

    private fun signUp(email: String, password: String, username: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    val userId = auth.uid.toString()
                    addUser(username, email, userId)
                    Toast.makeText(this, "Successfully registered!", Toast.LENGTH_LONG).show()
                    val loginIntent = Intent(this@RegisterActivity, Login::class.java)
                    startActivity(loginIntent)
                    return@addOnCompleteListener

                }

                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            }




    }
    private fun addUser(username: String, email: String, userid: String){
        val user = User(id = userid, username = username, email = email)
        AsyncTask.execute {
            AppDatabase.getInstance(applicationContext)!!
                .getAppDAO().insertUser(user)
        }


    }
}