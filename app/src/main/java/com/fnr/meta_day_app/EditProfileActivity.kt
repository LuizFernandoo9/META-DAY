package com.fnr.meta_day_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.fnr.meta_day_app.databinding.ActivityEditProfileBinding
import com.fnr.meta_day_app.databinding.ActivityProfileBinding


class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    private lateinit var salveNome: EditText
    private lateinit var salveTelefone: EditText
    private lateinit var salveEmail: EditText
    private lateinit var salveSenha: EditText
    private lateinit var salveConfirmeSenha: EditText
    private lateinit var btSalvar: Button
    private lateinit var arrowBackSalve: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        salveNome = findViewById(R.id.salve_nome)
        salveTelefone = findViewById(R.id.salve_telefone)
        salveEmail = findViewById(R.id.salve_email)
        salveSenha = findViewById(R.id.salve_senha)
        salveConfirmeSenha = findViewById(R.id.salve_confirmeSenha)
        btSalvar = findViewById(R.id.bt_salvar)
        arrowBackSalve = findViewById(R.id.arrow_backSalve)

        binding.arrowBackSalve.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btSalvar.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            //lógica salvar novos dados
        }
    }
}