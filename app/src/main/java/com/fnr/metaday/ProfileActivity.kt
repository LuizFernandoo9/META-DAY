package com.fnr.metaday

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameTextView = findViewById<TextView>(R.id.textViewName)
        val emailTextView = findViewById<TextView>(R.id.textViewEmail)
        val phoneTextView = findViewById<TextView>(R.id.textViewPhone)
        val editButton = findViewById<Button>(R.id.buttonEdit)
        val logoutButton = findViewById<Button>(R.id.buttonLogout)

        editButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        logoutButton.setOnClickListener {
            // Handle logout
            finish()
        }
    }
}
