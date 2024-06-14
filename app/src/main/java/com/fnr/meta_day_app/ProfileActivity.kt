package com.fnr.meta_day_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.fnr.meta_day_app.databinding.ActivityProfileBinding
import com.fnr.meta_day_app.databinding.ActivityResgistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var userNome: EditText
    private lateinit var userTelefone: EditText
    private lateinit var userEmail: EditText
    private lateinit var userSenha: EditText
    private lateinit var bt_sairConta: Button
    private lateinit var btEditar: Button
    private lateinit var arrowBackUser: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        userNome = findViewById(R.id.user_nome)
        userTelefone = findViewById(R.id.user_telefone)
        userEmail = findViewById(R.id.user_email)
        userSenha = findViewById(R.id.user_senha)
        btEditar = findViewById(R.id.bt_editar)
        bt_sairConta = findViewById(R.id.bt_sairConta)
        arrowBackUser = findViewById(R.id.arrow_backPerfil)

        loadUserData()

        binding.arrowBackPerfil.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
            //logica ir para tela principal
        }
        binding.btEditar.setOnClickListener{
            val intent = Intent(this, EditProfileActivity::class.java)
            finish()
            startActivity(intent)

        }
        binding.btSairConta.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
            //logica ir para tela de login
        }
    }
    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val docRef = db.collection("users").document(userId)
            docRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    userNome.setText(document.getString("nome"))
                    userTelefone.setText(document.getString("telefone"))
                    userEmail.setText(document.getString("email"))
                    userSenha.setText(document.getString("senha"))
                } else {
                    Toast.makeText(this, "Documento não encontrado", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this, "Erro ao obter dados: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
        }
    }
}
