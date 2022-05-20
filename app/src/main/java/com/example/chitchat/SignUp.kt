package com.example.chitchat

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSignup: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        edtEmail = findViewById(R.id.edt_Email)
        edtPass = findViewById(R.id.edt_Pass)
        edtName = findViewById(R.id.userName)
        btnSignup = findViewById(R.id.signup_Btn)

        mAuth = FirebaseAuth.getInstance()

        btnSignup.setOnClickListener{
            val email = edtEmail.text.toString()
            val pass = edtPass.text.toString()

            signup(email, pass)
        }

    }

    private fun signup(email: String, pass: String) {
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@SignUp,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp,"Sign-Up Failed", Toast.LENGTH_LONG).show()
                }
            }
    }
}