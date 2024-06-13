package com.fnr.meta_day_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fnr.meta_day_app.R
import com.fnr.meta_day_app.databinding.ActivityTelaLoginEmailBinding

class telaLoginEmail : AppCompatActivity() {

    private lateinit var binding: ActivityTelaLoginEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTelaLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btEntrar.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            finish()
            startActivity(intent)

        }
    }
}