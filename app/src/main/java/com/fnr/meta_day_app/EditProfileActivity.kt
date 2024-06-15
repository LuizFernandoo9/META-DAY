package com.fnr.meta_day_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.fnr.meta_day_app.databinding.ActivityEditProfileBinding
import com.fnr.meta_day_app.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore


class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

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

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        salveNome = findViewById(R.id.salve_nome)
        salveTelefone = findViewById(R.id.salve_telefone)
        salveEmail = findViewById(R.id.salve_email)
        salveSenha = findViewById(R.id.salve_senha)
        salveConfirmeSenha = findViewById(R.id.salve_confirmeSenha)
        btSalvar = findViewById(R.id.bt_salvar)
        arrowBackSalve = findViewById(R.id.arrow_backSalve)

        loadUserData()


        binding.arrowBackSalve.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btSalvar.setOnClickListener{
            updateUserProfile()
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            //lógica salvar novos dados
        }
    }

    private fun loadUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val docRef = db.collection("users").document(userId)
            docRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    salveNome.setText(document.getString("nome"))
                    salveTelefone.setText(document.getString("telefone"))
                    salveEmail.setText(document.getString("email"))
                    salveSenha.setText(document.getString("senha"))
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

    private fun updateUserProfile() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val nome = salveNome.text.toString().trim()
            val telefone = salveTelefone.text.toString().trim()
            val email = salveEmail.text.toString().trim()
            val senha = salveSenha.text.toString().trim()
            val confirmeSenha = salveConfirmeSenha.text.toString().trim()

            if (nome.isNotEmpty() && telefone.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty() && senha == confirmeSenha) {
                val user = hashMapOf(
                    "nome" to nome,
                    "telefone" to telefone,
                    "email" to email,
                    "senha" to senha
                )

                db.collection("users").document(userId)
                    .set(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show()

                        // Atualizar o perfil do usuário no Firebase Authentication
                        val userAuth = auth.currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(nome)
                            .build()

                        userAuth?.updateProfile(profileUpdates)?.addOnCompleteListener { profileTask ->
                            if (profileTask.isSuccessful) {

                                userAuth.updatePassword(senha)?.addOnCompleteListener { pwdTask ->
                                    if (pwdTask.isSuccessful) {
                                        // Navegar para ProfileActivity
                                        val intent = Intent(this, ProfileActivity::class.java)
                                        startActivity(intent)
                                    } else {
                                        Toast.makeText(this, "Erro ao atualizar senha: ${pwdTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }


                            } else {
                                Toast.makeText(this, "Erro ao atualizar perfil: ${profileTask.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Falha ao atualizar dados: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
        }
    }
}