package com.fnr.meta_day_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fnr.meta_day_app.R
import com.fnr.meta_day_app.databinding.ActivityMainBinding
import com.fnr.meta_day_app.recuperarSenha
import com.fnr.meta_day_app.telaLoginEmail

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.entrarEmail.setOnClickListener {
            val segundaTela = Intent(this, telaLoginEmail::class.java)
            finish()
            startActivity(segundaTela)
        }
        binding.esqueciSenha.setOnClickListener {
            val terceiraTela = Intent(this, recuperarSenha::class.java)
            finish()
            startActivity(terceiraTela)
        }
        binding.telaRegistro.setOnClickListener {
            val quartaTela = Intent(this, ResgistrationActivity::class.java)
            finish()
            startActivity(quartaTela)
        }

    }
}