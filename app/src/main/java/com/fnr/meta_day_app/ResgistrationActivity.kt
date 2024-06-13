package com.fnr.meta_day_app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.fnr.meta_day_app.databinding.ActivityResgistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore


class ResgistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResgistrationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var editNome: EditText
    private lateinit var editTelefone: EditText
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var editConfirmeSenha: EditText
    private lateinit var btCadastrar: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var arrowBackRegistrar: TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityResgistrationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        editNome = findViewById(R.id.edit_nome)
        editTelefone = findViewById(R.id.edit_telefone)
        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        editConfirmeSenha = findViewById(R.id.edit_confirmeSenha)
        btCadastrar = findViewById(R.id.bt_cadastrar)
        progressBar = findViewById(R.id.progressbar)
        arrowBackRegistrar = findViewById(R.id.arrow_backRegistrar)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.btCadastrar.setOnClickListener {
            binding.btCadastrar.setOnClickListener {
                // Validação dos campos de entrada
                val nome = editNome.text.toString().trim()
                val telefone = editTelefone.text.toString().trim()
                val email = editEmail.text.toString().trim()
                val senha = editSenha.text.toString().trim()
                val confirmeSenha = editConfirmeSenha.text.toString().trim()

                // Verificação se todos os campos estão preenchidos corretamente
                if (nome.isNotEmpty() && telefone.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty() && senha == confirmeSenha) {
                    progressBar.visibility = View.VISIBLE

                    auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this) { task ->
                        progressBar.visibility = View.INVISIBLE
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid
                            val user = hashMapOf(
                                "nome" to nome,
                                "telefone" to telefone,
                                "email" to email
                            )
                            if (userId != null) {
                                db.collection("users").document(userId)
                                    .set(user)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()
                                        // Navegar para MainActivity
                                        val intent = Intent(this, MainActivity::class.java)
                                        finish()
                                        startActivity(intent)
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(this, "Falha ao salvar dados: ${e.message}", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(this, "Erro ao obter ID do usuário.", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            // Se o registro falhar, mostrar mensagem para o usuário.
                            if (task.exception is FirebaseAuthUserCollisionException) {
                                // O email já está em uso
                                Toast.makeText(this, "Este email já está em uso. Tente outro email.", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Falha no registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Por favor, insira um email válido.", Toast.LENGTH_SHORT).show()
                }
            }
            binding.arrowBackRegistrar.setOnClickListener {
                //lógica para mandar para tela inicial(rafa)
                val intent = Intent(this, ProfileActivity::class.java)
                finish()
                startActivity(intent)
            }

            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Atualizar o estado do botão de cadastro conforme o usuário digita
                    val nome = editNome.text.toString().trim()
                    val telefone = editTelefone.text.toString().trim()
                    val email = editEmail.text.toString().trim()
                    val senha = editSenha.text.toString().trim()
                    val confirmeSenha = editConfirmeSenha.text.toString().trim()

                    btCadastrar.isEnabled = nome.isNotEmpty() && telefone.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty() && senha == confirmeSenha
                }
                override fun afterTextChanged(s: Editable?) {}
            }

            editNome.addTextChangedListener(textWatcher)
            editTelefone.addTextChangedListener(textWatcher)
            editEmail.addTextChangedListener(textWatcher)
            editSenha.addTextChangedListener(textWatcher)
            editConfirmeSenha.addTextChangedListener(textWatcher)
        }

    }

}

